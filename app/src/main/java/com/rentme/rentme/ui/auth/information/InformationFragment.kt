package com.rentme.rentme.ui.auth.information

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rentme.rentme.ui.main.MainActivity
import com.rentme.rentme.databinding.FragmentInformationBinding

class InformationFragment: Fragment() {
    private lateinit var binding : FragmentInformationBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInformationBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       initView()
    }
    private fun initView() {
       binding.registration.setOnClickListener {
           startMainActivity()
       }
    }

    private fun startMainActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
    }


}