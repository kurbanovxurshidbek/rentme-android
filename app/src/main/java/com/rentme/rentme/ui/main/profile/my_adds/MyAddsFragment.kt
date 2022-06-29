package com.rentme.rentme.ui.main.profile.my_adds

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.rentme.rentme.R
import com.rentme.rentme.adapter.MyAddAdapter
import com.rentme.rentme.databinding.FragmentMyAddsBinding
import com.rentme.rentme.model.Result
import com.rentme.rentme.ui.auth.LoginActivity
import com.rentme.rentme.ui.main.MainActivity
import com.rentme.rentme.ui.profile.BottomSheetFragmentLogOut
import com.rentme.rentme.utils.UiStateObject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyAddsFragment : Fragment() {
    private val adapter by lazy { MyAddAdapter() }
    private val viewModel: MyAddsViewModel by viewModels()
    private val TAG = this::class.java.simpleName

    private var _binding: FragmentMyAddsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMyAddsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        binding.rvMyAdds.layoutManager = GridLayoutManager(requireContext(), 1)
        viewModel.getMyAddList()
        binding.rvMyAdds.adapter = adapter

        adapter.onClick = {result ->
            findNavController().navigate(R.id.detailsFragment,
                bundleOf("selectAdvertisement" to Gson().toJson(result))
            )
        }

        adapter.deleteA = { id ->
            val bottomSheetFragmentLogOut = BottomSheetFragmentLogOut("Do you want to delete this Advertisement?")
            bottomSheetFragmentLogOut.show((requireActivity() as MainActivity).supportFragmentManager,"BottomSheetfragmentLogOut")

            bottomSheetFragmentLogOut.clickYes = {
                viewModel.deleteAdvertisement(id)
                bottomSheetFragmentLogOut.dismiss()
            }
        }

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun setupObservers(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.stateMyAddList.collect{
                    when(it){
                        is UiStateObject.LOADING -> {}
                        is UiStateObject.SUCCESS -> {
                            adapter.submitData(it.data.data.data)
                            viewModel.getMyAddList()
                        }
                        is UiStateObject.ERROR ->{
                            Log.d(TAG, "Error MyAddList:" + it.message) }
                        else -> Unit
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.stateDeleteA.collect{
                    when(it){
                        is UiStateObject.LOADING -> {}
                        is UiStateObject.SUCCESS -> {
                            Toast.makeText(requireContext(), "This Advertisement has been Successfully deleted!!", Toast.LENGTH_SHORT).show()
                        }
                        is UiStateObject.ERROR ->{
                            Log.d(TAG, "Error deleteA:" + it.message) }
                        else -> Unit
                    }
                }
            }
        }

    }


}