package com.rentme.rentme.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.rentme.rentme.MainActivity
import com.rentme.rentme.R
import com.rentme.rentme.adapter.DetailPhotoAdapter
import com.rentme.rentme.databinding.ActivityDetailsBinding
import com.rentme.rentme.model.DetailPhoto

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val adapter by lazy { DetailPhotoAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN or WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)

        initViews()
        getAllDetailPhoro()
    }

    private fun initViews() {
        binding.rvDetailImages.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false )
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvDetailImages)
        binding.rvDetailImages.adapter = adapter


        binding.ivBackToDetails.setOnClickListener {
            finish()
        }


    }

    private fun getAllDetailPhoro(){
        val items:ArrayList<DetailPhoto> = ArrayList()
        items.add(DetailPhoto(R.drawable.im_malibu))
        items.add(DetailPhoto(R.drawable.im_malibu))
        items.add(DetailPhoto(R.drawable.im_malibu))
        items.add(DetailPhoto(R.drawable.im_malibu))

        adapter.sumbitData(items)
    }
}