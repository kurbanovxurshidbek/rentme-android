package com.rentme.rentme.ui.main.location

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.rentme.rentme.R
import com.rentme.rentme.databinding.FragmentSelectLocationBinding

class SelectLocationFragment : Fragment() {

    private var _binding: FragmentSelectLocationBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSelectLocationBinding.inflate(LayoutInflater.from(container!!.context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.llCurrentLocation.setOnClickListener {
            findNavController().navigate(R.id.mapsFragment)
        }

        fragmentManager?.setFragmentResultListener("location", viewLifecycleOwner, object : FragmentResultListener{
            override fun onFragmentResult(requestKey: String, result: Bundle) {
//                Log.d("Location", result.getString("location", "lll"))
                binding.tvCurrentLocation.text = result.getString("location", "Current Location")
                binding.ivBack.setImageResource(R.drawable.ic_check_24)
                binding.ivBack.setOnClickListener {
                    val result1 = Bundle().apply {
                        putString("location", binding.tvCurrentLocation.text.toString())
                    }
                    setFragmentResult("locationResult", result1)
                    findNavController().navigateUp()
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


}