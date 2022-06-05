package com.rentme.rentme.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.rentme.rentme.R
import com.rentme.rentme.adapter.DetailPhotoAdapter
import com.rentme.rentme.databinding.FragmentDetailsBinding
import com.rentme.rentme.model.DetailPhoto
import com.rentme.rentme.ui.home.HomeFragment
import java.util.*
import kotlin.collections.ArrayList

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val adapter by lazy { DetailPhotoAdapter() }
    private var photoList = ArrayList<DetailPhoto>()
    private lateinit var rvDetailImages: RecyclerView

    private val binding get() = _binding!!

    private var timer: Timer? = null
    private var timerTask: TimerTask? = null
    private var position: Int = 0
    private lateinit var layoutManagerBanner: LinearLayoutManager

    companion object {
        const val DELAY_MS: Long = 2500 //delay in milliseconds before task is to be executed
        const val PERIOD_MS: Long = 5000 // time in milliseconds between successive task executions.
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        runAutoScrollBanner()
    }

    override fun onPause() {
        super.onPause()
        stopAutoScrollBanner()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        getAllDetailPhoro()

    }

    private fun initViews() {
        binding.rvDetailImages.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvDetailImages.adapter = adapter

        binding.ivBackToDetails.setOnClickListener {
            requireActivity().onBackPressed()
        }

        setUpBanner()
    }

    private fun setUpBanner(){
        rvDetailImages = binding.rvDetailImages
        layoutManagerBanner = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvDetailImages.layoutManager = layoutManagerBanner
        rvDetailImages.adapter = adapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rvDetailImages)
        binding.dotsIndicatorDetails.attachToRecyclerView(rvDetailImages)
        rvDetailImages.smoothScrollBy(5, 0)
        rvDetailImages.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                position = layoutManagerBanner.findFirstCompletelyVisibleItemPosition()
            }
        })
    }

    private fun runAutoScrollBanner() {
        if (timer == null && timerTask == null) {
            timer = Timer()
            timerTask = object : TimerTask() {
                override fun run() {
                    if (position == adapter.itemCount - 1) {
                        position = 0
                        rvDetailImages.smoothScrollToPosition(position)
                    } else {
                        rvDetailImages.smoothScrollToPosition(++position)
                    }
                }
            }
            timer!!.schedule(timerTask, HomeFragment.DELAY_MS, HomeFragment.PERIOD_MS)
        }
    }

    private fun stopAutoScrollBanner(){
        if (timer != null && timerTask != null){
            timer!!.cancel()
            timer = null
            timerTask!!.cancel()
            timerTask = null
            position = layoutManagerBanner.findFirstCompletelyVisibleItemPosition()
        }
    }

    private fun getAllDetailPhoro(){
        val items:ArrayList<DetailPhoto> = ArrayList()
        items.add(DetailPhoto(R.drawable.im_tesla_model3))
        items.add(DetailPhoto(R.drawable.im_mersades))
        items.add(DetailPhoto(R.drawable.im_malibu))
        items.add(DetailPhoto(R.drawable.im_tesla_model3))

        photoList.addAll(items)
        adapter.sumbitData(photoList)
    }

}