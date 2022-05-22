package com.rentme.rentme.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.rentme.rentme.R
import com.rentme.rentme.databinding.ActivitySplashBinding
import com.rentme.rentme.ui.localization.LocalizationActivity

class SplashActivity : AppCompatActivity() {
    private val SPLASH_SCREEN = 5000
    var bottom: Animation? = null
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        //Animation
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        //Hooks
        binding.carRunning.animation = bottom


        Handler().postDelayed({
            val intent = Intent(this, LocalizationActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_SCREEN.toLong())
    }
}