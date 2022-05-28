package com.rentme.rentme.ui.upload

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.rentme.rentme.R
import com.rentme.rentme.databinding.FragmentUploadBinding
import com.rentme.rentme.ui.features.FeatureFragment

class UploadFragment : Fragment() {
    private var _binding: FragmentUploadBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUploadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectTypeSpinner()
        openFeatureActivity()

        binding.llLocation.setOnClickListener {
            findNavController().navigate(R.id.selectLocationFragment)
        }

//        Log.d("args", arguments?.getString("location", "")!!)
        if (!arguments?.getString("location", "").isNullOrEmpty()) {
            Log.d("args", "--" + arguments?.getString("location", "")!!)
            binding.tvLocation.text = arguments?.getString("location", "")
            findNavController().clearBackStack(R.id.mapsFragment)
        }
    }

    private fun openFeatureActivity() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.featureFragment)
        }
    }

    private fun selectTypeSpinner(){
        val types: ArrayList<String> = ArrayList()
        types.add("Yengil moshina")
        types.add("Yuk moshina")
        types.add("Velesiped")
        types.add("Skutor")

        binding.spnType.adapter = ArrayAdapter<String>(requireContext(), R.layout.spinner_item_view, types)
        binding.spnType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedItem = p0!!.getItemAtPosition(p2)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

    }


}