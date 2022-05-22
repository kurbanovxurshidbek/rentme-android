package com.rentme.rentme.ui.update

import android.R
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rentme.rentme.databinding.ActivityUpdateBinding


class UpdateActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSendSms.setOnClickListener {
            sendSmsToUpdateNumber()
        }
        binding.btnSaveSms.setOnClickListener {
            saveUpdatedNumber()
        }
        binding.ivBack.setOnClickListener { finish() }
    }

    private fun saveUpdatedNumber() {
        Toast.makeText(this, "Phone number changed successfully", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun sendSmsToUpdateNumber() {
        binding.llCurrentNumber.visibility = View.GONE
        binding.llNewNumber1.visibility = View.GONE
        binding.btnSendSms.visibility = View.GONE
        binding.btnSaveSms.visibility = View.VISIBLE
        binding.llNewNumber2.visibility = View.VISIBLE
        binding.llSmsConsfirm.visibility = View.VISIBLE
    }


}