package com.rentme.rentme.ui.localization

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rentme.rentme.databinding.ActivityLocalizationBinding
import com.rentme.rentme.ui.logIn.LogInActivity

class LocalizationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocalizationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocalizationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.engLanguage.setOnClickListener {
            startLoginActivity()
        }
    }

    fun startLoginActivity() {
        var intent = Intent(this, LogInActivity::class.java)
        startActivity(intent)
    }
}