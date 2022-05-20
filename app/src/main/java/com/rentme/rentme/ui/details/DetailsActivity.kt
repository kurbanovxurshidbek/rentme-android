package com.rentme.rentme.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rentme.rentme.R
import com.rentme.rentme.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}