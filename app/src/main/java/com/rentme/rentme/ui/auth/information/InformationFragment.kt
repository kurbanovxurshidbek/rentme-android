package com.rentme.rentme.ui.auth.information

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.rentme.rentme.ui.main.MainActivity
import com.rentme.rentme.R
import com.rentme.rentme.databinding.FragmentInformationBinding

class InformationFragment : Fragment() {
    private var result = ""
    private lateinit var binding: FragmentInformationBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("from_profile_page") { key, bundle ->
            result = bundle.getString("data")!!
        }

    }

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
        if (result == "from_profile_page") {
            binding.registration.text  = "Save"
            binding.ivLogo.visibility = View.GONE
        } else {
            initView()
        }
    }

    private fun initView() {
         binding.registration.setOnClickListener {
        binding.registration.setOnClickListener {
//            if (binding.edtFirstNameChild.text.toString().) {
//
//            }
            startMainActivity()
        }
        binding.edtFirstNameChild.addTextChangedListener(textWatcher)
        binding.edtLastNameChild.addTextChangedListener(textWatcher)
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (binding.edtFirstNameChild.text?.toString()!!
                    .isNotEmpty() && binding.edtLastNameChild.text?.toString()!!.isNotEmpty()
            ) {
                binding.registration.isEnabled = true
                binding.registration.isClickable = true
                binding.registration.setBackgroundResource(R.drawable.button_background_rounded_border)
            } else {
                binding.registration.isEnabled = false
                binding.registration.isClickable = false
                binding.registration.setBackgroundResource(R.drawable.button_background_not_click)
            }
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }

    private fun startMainActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
    }


}