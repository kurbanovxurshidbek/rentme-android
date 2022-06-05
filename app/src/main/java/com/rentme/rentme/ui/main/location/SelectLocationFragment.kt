package com.rentme.rentme.ui.main.location

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.rentme.rentme.R
import com.rentme.rentme.adapter.HistoryLocationAdapter
import com.rentme.rentme.data.local.entity.History
import com.rentme.rentme.databinding.FragmentSelectLocationBinding
import com.rentme.rentme.utils.UiStateList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectLocationFragment : Fragment() {

    private val locationViewModel: LocationViewModel by viewModels()
    private val historyLocationAdapter by lazy { HistoryLocationAdapter() }
    private var _binding: FragmentSelectLocationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSelectLocationBinding.inflate(
            LayoutInflater.from(container!!.context),
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        binding.llCurrentLocation.setOnClickListener {
            findNavController().navigate(R.id.mapsFragment)
        }

        fragmentManager?.setFragmentResultListener(
            "location",
            viewLifecycleOwner,
            object : FragmentResultListener {
                override fun onFragmentResult(requestKey: String, result: Bundle) {
//                Log.d("Location", result.getString("location", "lll"))
                    binding.tvCurrentLocation.text =
                        result.getString("location", "Current Location")
                    binding.ivBack.setImageResource(R.drawable.ic_check_24)
                    binding.ivBack.setOnClickListener {
                        val history = History(name = binding.tvCurrentLocation.text.toString())
                        locationViewModel.saveHistory(history)
                        setResultForFragment(history)
                    }
                }
            })

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initViews() {
        binding.rvHistory.apply {
            layoutManager = GridLayoutManager(requireContext(), 1)
            adapter = historyLocationAdapter
        }
        historyLocationAdapter.onClick = {
            setResultForFragment(it)
        }
        locationViewModel.getHistory()
        setUpObserves()
    }

    private fun setResultForFragment(history: History){
        val result1 = Bundle().apply {
            putString("location", history.name)
        }
        setFragmentResult("locationResult", result1)
        findNavController().navigateUp()
    }

    private fun setUpObserves() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            locationViewModel.locationState.collect {
                when (it) {
                    is UiStateList.LOADING -> {}
                    is UiStateList.SUCCESS -> {
                        Log.d("History", it.data.toString())
                        historyLocationAdapter.submitData(it.data)
                    }
                    is UiStateList.ERROR -> {
                        Log.d("History", it.message)
                    }
                    else -> Unit
                }
            }
        }
    }

}