package com.rentme.rentme.ui.main.filter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.rentme.rentme.R
import com.rentme.rentme.adapter.ResultAdapter
import com.rentme.rentme.databinding.FragmentResultBinding
import com.rentme.rentme.model.Result
import com.rentme.rentme.model.filtermodel.Advertisement


class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val adapter by lazy { ResultAdapter() }

    private val binding get() = _binding!!
    private var list = ArrayList<Advertisement>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val arg = arguments?.getString("data") ?: ""
        list = arrayListOf(Gson().fromJson(arg,Advertisement::class.java))

        setFragmentResultListener("model_name"){key,bundle->
            val result = bundle.getString("data")
            //result is the modelname of the car from SearchFragment
            Log.d("OnRsultFragment", "onCreate: $result")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(requireContext(), list.toString(), Toast.LENGTH_SHORT).show()
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
        items.add(Result(R.drawable.im_malibu,"Malibu 2","50$","200$",))
        items.add(Result(R.drawable.im_tesla_model3,"Model 3","10","350$",))
        items.add(Result(R.drawable.im_mersades,"AMG","30","400$",))
        items.add(Result(R.drawable.im_tesla_model3,"Model 3","25","300$",))
        items.add(Result(R.drawable.im_mersades,"AMG 2","40$","350$",))

    }

}