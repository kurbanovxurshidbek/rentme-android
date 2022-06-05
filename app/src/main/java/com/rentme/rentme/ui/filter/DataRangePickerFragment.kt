package com.rentme.rentme.ui.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rentme.rentme.R
import com.rentme.rentme.databinding.FragmentDataRangePickerBinding
import com.rentme.rentme.databinding.FragmentFilterBinding


class DataRangePickerFragment : Fragment() {

    private var _binding: FragmentDataRangePickerBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDataRangePickerBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {

    }


}