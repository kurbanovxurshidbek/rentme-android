package com.rentme.rentme.ui.auth.localization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rentme.rentme.R
import com.rentme.rentme.databinding.FragmentLocalizationBinding

class LocalizationFragment: Fragment() {
    private lateinit var binding : FragmentLocalizationBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocalizationBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       initView()
    }
    private fun initView() {
        binding.engLanguage.setOnClickListener {
            findNavController().navigate(R.id.logInFragment)
        }
    }


}