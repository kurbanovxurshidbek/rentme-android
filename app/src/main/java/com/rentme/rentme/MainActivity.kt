package com.rentme.rentme

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.rentme.rentme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var isLightStatusBar: Boolean = false

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

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.homeFragment, R.id.uploadFragment, R.id.profileFragment -> showBottomNavigation()
                else -> hideBottomNavigation()
            }
            //                Log.d("@@@", "${controller.currentDestination!!.displayName} -- ${destination.displayName}")
        }
    }

    override fun onBackPressed() {
        if (navController.currentDestination!!.id == R.id.homeFragment)
            finish()
        super.onBackPressed()
    }

    fun clearLightStatusBar() {
        if (!isLightStatusBar && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = Color.TRANSPARENT
            isLightStatusBar = true
        }

    }

    fun setLightStatusBar() {
        if (isLightStatusBar && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.statusBarColor = Color.WHITE

            var flags: Int = this.window.decorView.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR // add LIGHT_STATUS_BAR to flag
            this.window.decorView.systemUiVisibility = flags
            this.window.statusBarColor = ContextCompat.getColor(this, R.color.white)
            isLightStatusBar = false

        }
    }

    fun showBottomNavigation() {
        binding.bnvMain.visibility = View.VISIBLE
    }

    fun hideBottomNavigation() {
        binding.bnvMain.visibility = View.GONE
    }


    @SuppressLint("ServiceCast")
    fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}