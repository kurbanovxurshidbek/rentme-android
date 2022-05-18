package com.rentme.rentme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.rentme.rentme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews(){
        val bnv = binding.bnvMain
        navController =findNavController(R.id.nav_host_fragment_container)

        bnv.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        if (navController.currentDestination!!.id == R.id.homeFragment)
            finish()
        super.onBackPressed()
    }
}