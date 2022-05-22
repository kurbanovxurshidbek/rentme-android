package com.rentme.rentme.ui.details.information

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rentme.rentme.MainActivity
import com.rentme.rentme.R
import com.rentme.rentme.databinding.ActivityInformationBinding
import com.rentme.rentme.databinding.ActivityLogInBinding

class InformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.registration.setOnClickListener {
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}