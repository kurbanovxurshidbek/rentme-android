package com.rentme.rentme.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.rentme.rentme.R
import com.rentme.rentme.data.local.entity.BrandListEntity
import com.rentme.rentme.data.local.entity.ModelsListEntity
import com.rentme.rentme.databinding.ActivitySplashBinding
import com.rentme.rentme.repository.SplashRepository
import com.rentme.rentme.ui.auth.LoginActivity
import com.rentme.rentme.utils.UiStateObject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private  val viewModel by viewModels<SplashViewModel>()
    private val SPLASH_SCREEN = 1500
    var bottom: Animation? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setLightStatusBar()
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN or WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
        initView()
        setUpObservers()
    }


    private fun initView() {
        //Animation
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        //Hooks

        //   binding.llRentme.animate().translationY(-1400F).setDuration(2700).setStartDelay(0)
        Handler().postDelayed({
        val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_SCREEN.toLong())

        viewModel.getModelListS()  // get all model list from server and save to Local
        viewModel.getBrandList()  // get all brand list from server and save to Local
    }


    private fun setUpObservers() {
        lifecycleScope.launchWhenStarted{
            viewModel.stateModelListS.collect{
                when(it){
                    is UiStateObject.LOADING -> {}
                    is UiStateObject.SUCCESS ->{
                        Log.d("data splash", it.data.data.data.size.toString())
                        it.data.data.data.forEach {  model ->
                            viewModel.saveModelToLocal(ModelsListEntity(modelName = model))
                        }
                    }
                    is UiStateObject.ERROR ->{
                        Log.d("@SPlASHACTIVITY", "setUpO Models: ${it.message}")}
                    else -> Unit
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.stateBrandList.collect{
                when(it){
                    is UiStateObject.LOADING -> {}
                    is UiStateObject.SUCCESS ->{
                        Log.d("data splash", it.data.data.data.size.toString())
                        it.data.data.data.forEach {  brand ->
                            val brandEntity = BrandListEntity(brand.name!!, brand.image!!, brand.models!!)
                            viewModel.saveBrandToLocal(brandEntity)
                        }
                    }
                    is UiStateObject.ERROR ->{
                        Log.d("@SPlASHACTIVITY", "setUpO Brands: ${it.message}")}
                    else -> Unit
                }
            }
        }
    }

    private fun setLightStatusBar() {
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