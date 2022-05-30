package com.rentme.rentme.ui.details

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.ahmadhamwi.tabsync.TabbedListMediator
import com.google.android.material.tabs.TabLayout
import com.rentme.rentme.R
import com.rentme.rentme.adapter.DetailPhotoAdapter
import com.rentme.rentme.databinding.FragmentDetailsBinding
import com.rentme.rentme.model.DetailPhoto

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val adapter by lazy { DetailPhotoAdapter() }
    private var photoList = ArrayList<DetailPhoto>()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        getAllDetailPhoro()
        tabLayoutManager()

    }

    private fun initViews() {
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvDetailImages)
        binding.rvDetailImages.adapter = adapter


        binding.ivBackToDetails.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.llLocationDetails.setOnClickListener {
            findNavController().navigate(R.id.selectLocationFragment)
        }

    }

    private fun tabLayoutManager() {
        binding.apply {
            if (photoList.size == 1) {
                tabLayout.isVisible = false
            }

            for (category in 0 until photoList.size) {
                tabLayout.addTab(binding.tabLayout.newTab())
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1)
                else tabLayout.getTabAt(category)?.text = "⬤"
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1)
                tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_circle_select)

            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    rvDetailImages.smoothScrollToPosition(tab!!.position)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1)
                        allTabLayoutIndicatorUnselected()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1)
                        tabLayout.getTabAt(tab.position)?.setIcon(R.drawable.ic_circle_select)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })


            rvDetailImages.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    Log.d("TAGS", "onScrollStateChanged: ok")
                    val layoutManager =
                        LinearLayoutManager::class.java.cast(recyclerView.layoutManager)
                    val lastVisible = layoutManager!!.findLastVisibleItemPosition()
                    Log.d("TAGS", "onScrollStateChanged: $lastVisible")
                    tabLayout.setScrollPosition(lastVisible, 0f, true)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1)
                        allTabLayoutIndicatorUnselected()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1)
                        tabLayout.getTabAt(lastVisible)?.setIcon(R.drawable.ic_circle_select)
                }
            })

            TabbedListMediator(
                rvDetailImages,
                tabLayout,
                photoList.indices.toList(),
                true
            )
        }
    }

    private fun allTabLayoutIndicatorUnselected() {
        for (category in 0 until photoList.size) {
            binding.tabLayout.getTabAt(category)?.setIcon(R.drawable.ic_circle_unselect)
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