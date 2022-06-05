package com.rentme.rentme.ui.auth.information

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.rentme.rentme.ui.main.MainActivity
import com.rentme.rentme.MainActivity
import com.rentme.rentme.R
import com.rentme.rentme.databinding.FragmentInformationBinding

class InformationFragment : Fragment() {
    private lateinit var binding: FragmentInformationBinding

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