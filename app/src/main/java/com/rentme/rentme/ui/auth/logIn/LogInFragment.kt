package com.rentme.rentme.ui.auth.logIn

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rentme.rentme.MainActivity
import com.rentme.rentme.R
import com.rentme.rentme.databinding.FragmentLogInBinding

class LogInFragment:Fragment(){
    private lateinit var binding : FragmentLogInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogInBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    private fun initView() {
        binding.btnSendSms.setOnClickListener {

            binding.llSmsAndSmsCode.visibility = View.VISIBLE
            binding.btnSendSms.visibility = View.GONE
            binding.btnContinueSms.visibility = View.VISIBLE
            binding.skip.visibility = View.GONE


        }

        binding.btnContinueSms.setOnClickListener {
            startInformationActivity()
        }

        binding.skip.setOnClickListener {
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
    }

    private fun startInformationActivity() {
       findNavController().navigate(R.id.informationFragment)
    }
}