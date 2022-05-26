package com.rentme.rentme.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rentme.rentme.MainActivity
import com.rentme.rentme.R
import com.rentme.rentme.adapter.HomeAdsAdapter
import com.rentme.rentme.adapter.SubMainAdapter
import com.rentme.rentme.databinding.FragmentHomeBinding
import com.rentme.rentme.model.Result
import com.rentme.rentme.ui.details.DetailsActivity
import com.rentme.rentme.ui.filter.FiltersActivity
import com.rentme.rentme.ui.types.TypesActivity

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null

    private lateinit var rvMainAds: RecyclerView
    private lateinit var rvMainLatest: RecyclerView
    private lateinit var rvMainDaily: RecyclerView
    private lateinit var rvMainWeekly: RecyclerView
    private lateinit var rvMainMonthly: RecyclerView
    private val adsAdapter by lazy { HomeAdsAdapter() }
    private val subAdapter by lazy { SubMainAdapter() }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclers()
        getAds()
        getAllResult()

        subAdapter.onClick = {result ->
            Intent(requireContext(), DetailsActivity::class.java).also {
                it.putExtra("carName", result.carName)
                startActivity(it)
            }
        }

        binding.fToFilter.setOnClickListener {
            startFilterActivity()
        }

        binding.llCategory1.setOnClickListener {
            startTypesActivity()
        }
//        binding.llCategory2.setOnClickListener {
//            startTypesActivity()
//        }
//        binding.llCategory3.setOnClickListener {
//            startTypesActivity()
//        }
//        binding.llCategory4.setOnClickListener {
//            startTypesActivity()
//        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setUpRecyclers(){
        rvMainAds = binding.rvMainAds
        rvMainLatest = binding.rvMainLatest
        rvMainDaily = binding.rvMainDayly
        rvMainWeekly = binding.rvMainWeekly
//        rvMainMonthly = binding.rvMainMonthly

        rvMainAds.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvMainDaily.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvMainLatest.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvMainWeekly.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        rvMainMonthly.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        rvMainAds.adapter = adsAdapter
        rvMainLatest.adapter = subAdapter
        rvMainDaily.adapter = subAdapter
        rvMainWeekly.adapter = subAdapter
//        rvMainMonthly.adapter = subAdapter
    }

    private fun startTypesActivity(){
        requireActivity().startActivity(Intent(requireContext(), TypesActivity::class.java))
    }

    private fun startFilterActivity(){
        requireActivity().startActivity(Intent(requireContext(), FiltersActivity::class.java))
    }

    private fun getAds(){
        val ads = ArrayList<Int>()
        ads.add(R.drawable.im_tesla_model3)
        ads.add(R.drawable.im_tesla_model3)
        ads.add(R.drawable.im_tesla_model3)

        adsAdapter.submitData(ads)
    }

    private fun getAllResult() {
        val items:ArrayList<Result> = ArrayList()
        items.add(Result(R.drawable.im_malibu,"Malibu 2","","200$",))
        items.add(Result(R.drawable.im_malibu,"Malibu 3","","250$",))
        items.add(Result(R.drawable.im_malibu,"Nexia 2","","100$",))
        items.add(Result())
        subAdapter.submitData(items)
    }
}