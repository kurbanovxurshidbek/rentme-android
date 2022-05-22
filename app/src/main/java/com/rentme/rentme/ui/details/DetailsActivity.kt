package com.rentme.rentme.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
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
//
//    class PagerDecorator : RecyclerView.ItemDecoration() {
//
//        private var paintStroke: Paint = Paint().apply {
//            style = Paint.Style.STROKE
//            strokeWidth = 4f
//            color = Color.WHITE
//        }
//
//        private val paintFill = Paint().apply {
//            style = Paint.Style.FILL
//            color = Color.WHITE
//        }
//
//        // save the center coordinates of all indicators
//        private val indicators = mutableListOf<Pair<Float, Float>>()
//
//        private val indicatorRadius = 30f
//        private val indicatorPadding = 180f
//
//        private var activeIndicator = 0
//        private var isInitialized = false
//
//        // create three indicators for three slides
//        override fun onDrawOver(canvas: Canvas,
//                                parent: RecyclerView,
//                                state: RecyclerView.State) {
//
//            if(!isInitialized) {
//                setupIndicators(parent)
//            }
//
//            // draw three indicators with stroke style
//            parent.adapter?.let {
//                with(canvas) {
//                    drawCircle(indicators[0].first, indicators[0].second)
//                    drawCircle(indicators[1].first, indicators[1].second)
//                    drawCircle(indicators[2].first, indicators[2].second)
//                }
//
//                val visibleItem = (parent.layoutManager as LinearLayoutManager)
//                    .findFirstCompletelyVisibleItemPosition()
//
//                if(visibleItem >= 0) {
//                    activeIndicator = visibleItem
//                }
//
//                // paint over the needed circle
//                when (activeIndicator) {
//                    0 -> canvas.drawCircle(indicators[0].first, indicators[0].second, true)
//                    1 -> canvas.drawCircle(indicators[1].first, indicators[1].second, true)
//                    2 -> canvas.drawCircle(indicators[2].first, indicators[2].second, true)
//                }
//            }
//        }
//
//        private fun Canvas.drawCircle(x: Float, y: Float, isFill: Boolean = false) {
//            drawCircle(x, y, indicatorRadius, if(isFill) paintFill else paintStroke)
//        }
//
//        private fun setupIndicators(recyclerView: RecyclerView) {
//            isInitialized = true
//
//            val indicatorTotalWidth = indicatorRadius * 6 + indicatorPadding
//            val indicatorPosX = (recyclerView.width - indicatorTotalWidth) / 2f
//            val indicatorPosY = recyclerView.height - (indicatorRadius * 6 / 2f)
//
//            indicators.add(indicatorPosX to indicatorPosY)
//            indicators.add((indicatorPosX + indicatorRadius) to indicatorPosY)
//            indicators.add((indicatorPosX + indicatorRadius * 2) to indicatorPosY)
//        }
//    }
}