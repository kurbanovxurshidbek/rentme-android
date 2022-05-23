package com.rentme.rentme.ui.splash

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.rentme.rentme.R
import com.rentme.rentme.databinding.ActivitySplashBinding
import com.rentme.rentme.ui.localization.LocalizationActivity

class SplashActivity : AppCompatActivity() {
    private val SPLASH_SCREEN = 500
    var bottom: Animation? = null
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setLightStatusBar()
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN or WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
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

    fun setLightStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.statusBarColor = Color.WHITE

            var flags: Int = this.window.decorView.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR // add LIGHT_STATUS_BAR to flag
            this.window.decorView.systemUiVisibility = flags
            this.window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        }
    }
}