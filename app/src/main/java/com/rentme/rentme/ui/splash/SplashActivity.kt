package com.rentme.rentme.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.rentme.rentme.R
import com.rentme.rentme.ui.logIn.LogInActivity

class SplashActivity : AppCompatActivity() {
    private val SPLASH_SCREEN = 5000
    var bottom: Animation? = null
    lateinit var image: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initView()
    }

    private fun initView() {
        //Animation
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)


        //Hooks
        image = findViewById(R.id.car_running)
        image.setAnimation(bottom)
        Handler().postDelayed({
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_SCREEN.toLong())
    }
}