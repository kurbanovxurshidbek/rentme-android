package com.rentme.rentme.ui.home

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.rentme.rentme.R
import com.rentme.rentme.adapter.HomeAdsAdapter
import com.rentme.rentme.adapter.SubMainAdapter
import com.rentme.rentme.databinding.FragmentHomeBinding
import com.rentme.rentme.model.Result
import java.util.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null

    private lateinit var rvMainAds: RecyclerView
    private lateinit var rvMainLatest: RecyclerView
    private lateinit var rvMainDaily: RecyclerView
    private lateinit var rvMainLongTerm: RecyclerView
    private val adsAdapter by lazy { HomeAdsAdapter() }
    private val subAdapter by lazy { SubMainAdapter() }

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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpBanner()
        setUpRecyclers()
        getAds()
        getAllResult()

        initViews()

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

    private fun initViews() {
        subAdapter.onClick = { result ->
            findNavController().navigate(R.id.detailsFragment)
        }

        binding.edtSearch.setOnClickListener {
            findNavController().navigate(R.id.searchFragment)
        }

        binding.fToFilter.setOnClickListener {
            findNavController().navigate(R.id.filterFragment)
        }

        binding.llCategory1.setOnClickListener {
            findNavController().navigate(R.id.typesFragment)
        }
    }

    private fun setUpRecyclers() {
        rvMainLatest = binding.rvMainLatest
        rvMainLatest.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvMainLatest.adapter = subAdapter

        rvMainDaily = binding.rvMainDaily
        rvMainDaily.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvMainDaily.adapter = subAdapter

        rvMainLongTerm = binding.rvMainLongTerm
        rvMainLongTerm.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvMainLongTerm.adapter = subAdapter
    }

    private fun setUpBanner(){
        rvMainAds = binding.rvMainAds
        layoutManagerBanner = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvMainAds.layoutManager = layoutManagerBanner
        rvMainAds.adapter = adsAdapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rvMainAds)
        binding.dotsIndicator.attachToRecyclerView(rvMainAds)
        rvMainAds.smoothScrollBy(5, 0)
        rvMainAds.addOnScrollListener(object : RecyclerView.OnScrollListener(){
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
                    if (position == adsAdapter.itemCount - 1) {
                        position = 0
                        rvMainAds.smoothScrollToPosition(position)
                    } else {
                        rvMainAds.smoothScrollToPosition(++position)
                    }
                }
            }
            timer!!.schedule(timerTask, DELAY_MS, PERIOD_MS)
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

    private fun getAds() {
        val ads = ArrayList<Int>()
        ads.add(R.drawable.im_tesla_model3)
        ads.add(R.drawable.im_tesla_model3)
        ads.add(R.drawable.im_tesla_model3)

        adsAdapter.submitData(ads)
    }

    private fun getAllResult() {
        val items: ArrayList<Result> = ArrayList()
        items.add(Result(R.drawable.im_malibu, "Malibu 2", "", "200$"))
        items.add(Result(R.drawable.im_malibu, "Malibu 3", "", "250$"))
        items.add(Result(R.drawable.im_malibu, "Nexia 2", "", "100$"))
        items.add(Result())
        subAdapter.submitData(items)
    }
}