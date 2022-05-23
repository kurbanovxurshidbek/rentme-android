package com.rentme.rentme.ui.logIn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.rentme.rentme.MainActivity
import com.rentme.rentme.R
import com.rentme.rentme.databinding.ActivityInformationBinding
import com.rentme.rentme.databinding.ActivityLocalizationBinding
import com.rentme.rentme.databinding.ActivityLogInBinding
import com.rentme.rentme.ui.details.information.InformationActivity

class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun startInformationActivity() {

        val intent = Intent(this, InformationActivity::class.java)
        startActivity(intent)
    }

}