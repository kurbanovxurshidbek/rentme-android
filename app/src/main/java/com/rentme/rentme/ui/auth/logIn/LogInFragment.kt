package com.rentme.rentme.ui.auth.logIn

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fraggjkee.smsconfirmationview.SmsConfirmationView
import com.rentme.rentme.ui.main.MainActivity
import com.rentme.rentme.R
import com.rentme.rentme.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {
    private lateinit var binding: FragmentLogInBinding
    //private val viewModel by viewModels<LoginViewModel> ()

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
            binding.btnSkip.visibility = View.GONE
        }
       binding.edtPhonenumber.addTextChangedListener(textWatchersendsms)
        binding.smsCodeView.onChangeListener =
            SmsConfirmationView.OnChangeListener { code, isComplete ->
                if (isComplete) {
                    binding.btnContinueSms.isClickable = true
                    binding.btnContinueSms.isEnabled = true
                    binding.btnContinueSms.setBackgroundResource(R.drawable.button_background_rounded_border)
                    Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show()
                } else {
                    binding.btnContinueSms.isClickable = false
                    binding.btnContinueSms.isEnabled = false
                    binding.btnContinueSms.setBackgroundResource(R.drawable.button_background_not_click)
                    //Toast.makeText(context, "Please, fill in the cells", Toast.LENGTH_SHORT).show()
                }
            }

        binding.btnContinueSms.setOnClickListener {
            startInformationActivity()

        }

        binding.btnSkip.setOnClickListener {
            startMainActivity()
        }

    }

    private val textWatchersendsms =object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (binding.edtPhonenumber.text?.length==13 ) {
                binding.btnSendSms.isEnabled = true
                binding.btnSendSms.isClickable = true
                binding.btnSendSms.setBackgroundResource(R.drawable.button_background_rounded_border)
            } else {
                binding.btnSendSms.isEnabled = false
                binding.btnSendSms.isClickable = false
                binding.btnSendSms.setBackgroundResource(R.drawable.button_background_not_click)
            }
        }

        override fun afterTextChanged(p0: Editable?) {

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