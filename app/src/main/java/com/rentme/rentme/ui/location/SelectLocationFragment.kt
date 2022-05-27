package com.rentme.rentme.ui.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rentme.rentme.R
import com.rentme.rentme.databinding.FragmentSelectLocationBinding

class SelectLocationFragment : Fragment() {

    private var _binding: FragmentSelectLocationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSelectLocationBinding.inflate(LayoutInflater.from(container!!.context), container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}