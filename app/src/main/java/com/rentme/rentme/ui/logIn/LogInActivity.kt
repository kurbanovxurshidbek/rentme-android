package com.rentme.rentme.ui.logIn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        binding.btnSendSms.setOnClickListener{
            startInformationActivity()
        }
    }

    private fun startInformationActivity() {
        val intent = Intent(this,InformationActivity::class.java)
        startActivity(intent)
    }
}