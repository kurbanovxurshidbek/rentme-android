package com.rentme.rentme.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.rentme.rentme.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
    }

    private fun initViews(){
        val navController = findNavController(R.id.nav_host_fragment_container)
    }
}