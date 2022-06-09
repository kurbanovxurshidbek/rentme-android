package com.rentme.rentme.ui.main.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.rentme.rentme.R
import com.rentme.rentme.adapter.BrandsAdapter
import com.rentme.rentme.adapter.HomeAdsAdapter
import com.rentme.rentme.adapter.ResultAdapter
import com.rentme.rentme.adapter.SubMainAdapter
import com.rentme.rentme.databinding.FragmentHomeBinding
import com.rentme.rentme.databinding.FragmentHomeDemoBinding
import com.rentme.rentme.model.Brands
import com.rentme.rentme.model.Model
import com.rentme.rentme.model.Result
import com.rentme.rentme.ui.main.MainActivity
import com.rentme.rentme.utils.UiStateObject
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeDemoBinding? = null
    private val binding get() = _binding!!

    private lateinit var rvMainAds: RecyclerView
    private lateinit var rvMainBrands: RecyclerView
    private lateinit var rvMainLatest: RecyclerView
    private lateinit var rvMainDaily: RecyclerView
    private lateinit var rvMainLongTerm: RecyclerView
    private val adsAdapter by lazy { HomeAdsAdapter() }
    private val subAdapter by lazy { SubMainAdapter() }
    private val resultAdapter by lazy { ResultAdapter() }
    private val brandsAdapter by lazy { BrandsAdapter() }


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
        _binding = FragmentHomeDemoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpBanner()
        setUpRecyclers()
        getBanners()
        getAllResult()
        getAllBrands()

        initViews()
        homeViewModel.getMainData()
        setUpObservers()

    }

    override fun onResume() {
        super.onResume()
        runAutoScrollBanner()
        (requireActivity() as MainActivity).closeKeyBoard()
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

    }

    private fun setUpRecyclers() {
        rvMainLatest = binding.rvMainLatest
        rvMainLatest.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvMainLatest.adapter = subAdapter

        rvMainDaily = binding.rvMainDaily
        rvMainDaily.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvMainDaily.adapter = resultAdapter
        rvMainDaily.addItemDecoration(SpacesItemDecoration(16))

        rvMainLongTerm = binding.rvMainLongTerm
        rvMainLongTerm.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvMainLongTerm.adapter = subAdapter

        rvMainBrands = binding.rvBrands
        rvMainBrands.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = brandsAdapter
//            addItemDecoration(SpacesItemDecoration(16))
        }
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

    private fun setUpObservers(){
        Log.d("Network", "observes")
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            homeViewModel.homeState.collect{
                when(it){
                    is UiStateObject.LOADING -> {

                    }
                    is UiStateObject.SUCCESS -> {
//                        Log.d("Network", "SUCCESS -- ${it.data}")
                    }
                    is UiStateObject.ERROR -> {
                        Log.d("Network", it.message)
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun getBanners() {
        val ads = ArrayList<Int>()
        ads.add(R.drawable.im_banner_2)
        ads.add(R.drawable.im_banner_1)
        ads.add(R.drawable.im_banner_4)
//        ads.add(R.drawable.im_banner_5)
//        ads.add(R.drawable.im_banner_6_0)
        ads.add(R.drawable.im_banner_7)

        adsAdapter.submitData(ads)
    }

    private fun getAllResult() {
        val items: ArrayList<Result> = ArrayList()
        items.add(Result(R.drawable.im_malibu, "Malibu 2", "", "200$"))
        items.add(Result(R.drawable.im_malibu, "Malibu 3", "", "250$"))
        items.add(Result(R.drawable.im_malibu, "Nexia 2", "", "100$"))
        items.add(Result(R.drawable.im_malibu, "Malibu 3", "", "250$"))
        items.add(Result(R.drawable.im_malibu, "Nexia 2", "", "100$"))
        resultAdapter.submitData(items)
//        items.add(Result())
        subAdapter.submitData(items)
    }

    private fun getAllBrands(){
        val brands = ArrayList<Brands>()
        brands.add(Brands(image = "https://www.carlogos.org/car-logos/tesla-logo.png", name = "Tesla", models = arrayListOf(Model())))
        brands.add(Brands(image = "https://www.carlogos.org/car-logos/bmw-logo.png", name = "BMW", models = arrayListOf(Model())))
        brands.add(Brands(image = "https://www.carlogos.org/car-logos/ferrari-logo.png", name = "Ferrari", models = arrayListOf(Model())))
        brands.add(Brands(image = "https://www.carlogos.org/car-logos/ford-logo.png", name = "Ford", models = arrayListOf(Model())))
        brands.add(Brands(image = "https://www.carlogos.org/car-logos/porsche-logo.png", name = "Porsche", models = arrayListOf(Model())))
        brands.add(Brands(image = "https://www.carlogos.org/car-logos/lamborghini-logo.png", name = "Lamborghini", models = arrayListOf(Model())))
        brands.add(Brands(image = "https://www.carlogos.org/car-logos/toyota-logo.png", name = "toyota", models = arrayListOf(Model())))

        brandsAdapter.submitData(brands)
    }
}