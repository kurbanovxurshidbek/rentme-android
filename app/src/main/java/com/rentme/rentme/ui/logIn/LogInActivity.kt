package com.rentme.rentme.ui.logIn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rentme.rentme.R
import com.rentme.rentme.databinding.ActivityInformationBinding
import com.rentme.rentme.databinding.ActivityLocalizationBinding

class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {

    }
}