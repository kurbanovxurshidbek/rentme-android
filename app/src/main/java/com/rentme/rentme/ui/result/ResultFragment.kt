package com.rentme.rentme.ui.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.rentme.rentme.R
import com.rentme.rentme.adapter.ResultAdapter
import com.rentme.rentme.databinding.FragmentResultBinding
import com.rentme.rentme.model.Result


class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val adapter by lazy { ResultAdapter() }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        getAllResult()
    }

    private fun initViews() {
        binding.rvResult.layoutManager = GridLayoutManager(requireContext(),1)
        binding.rvResult.adapter = adapter
        adapter.onClick = {result ->
            findNavController().navigate(R.id.detailsFragment)
        }

        binding.apply {
            llFilter.setOnClickListener {
                findNavController().navigate(R.id.filterFragment)
            }

            icBackToTypes.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

    private fun getAllResult() {
        val items:ArrayList<Result> = ArrayList()
        items.add(Result(R.drawable.im_malibu,"Malibu 2","","200$",))
        items.add(Result(R.drawable.im_tesla_model3,"Model 3","","350$",))
        items.add(Result(R.drawable.im_mersades,"AMG","","400$",))
        items.add(Result(R.drawable.im_tesla_model3,"Model 3","","300$",))
        items.add(Result(R.drawable.im_mersades,"AMG 2","","350$",))

        adapter.submitData(items)
    }

}