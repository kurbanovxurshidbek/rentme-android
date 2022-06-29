package com.rentme.rentme.ui.main.details

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.google.gson.Gson
import com.rentme.rentme.R
import com.rentme.rentme.adapter.DetailPhotoAdapter
import com.rentme.rentme.databinding.FragmentDetailsBinding
import com.rentme.rentme.model.DetailPhoto
import com.rentme.rentme.model.Type
import com.rentme.rentme.model.UploadAdvertisement
import com.rentme.rentme.model.filtermodel.Advertisement
import com.rentme.rentme.ui.main.MainActivity
import com.rentme.rentme.ui.profile.BottomSheetFragmentLogOut
import com.rentme.rentme.utils.UiStateObject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val adapter by lazy { DetailPhotoAdapter() }
    private lateinit var rvDetailImages: RecyclerView

    private val binding get() = _binding!!

    private var timer: Timer? = null
    private var timerTask: TimerTask? = null
    private var position: Int = 0
    private lateinit var layoutManagerBanner: LinearLayoutManager
    private var advertisement: Advertisement? = null

    private val viewModel by viewModels<DetailsViewModel>()
    private val TAG = this::class.java.simpleName


    companion object {
        const val DELAY_MS: Long = 2500 //delay in milliseconds before task is to be executed
        const val PERIOD_MS: Long = 5000 // time in milliseconds between successive task executions.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            advertisement = Gson().fromJson(it.getString("selectAdvertisement"), Advertisement::class.java)
        }
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

    }

    private fun initViews() {
        binding.rvDetailImages.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter.sumbitData(advertisement!!.transport.pictures)
        binding.rvDetailImages.adapter = adapter

        binding.ivBackToDetails.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.ivFavoriteDelete.setOnClickListener {
            when (findNavController().previousBackStackEntry!!.destination.id) {
                R.id.myAddsFragment -> {
                    val bottomSheetFragmentLogOut = BottomSheetFragmentLogOut("Do you want to delete this Advertisement?")
                    bottomSheetFragmentLogOut.show((requireActivity() as MainActivity).supportFragmentManager,"BottomSheetfragmentLogOut")

                    bottomSheetFragmentLogOut.clickYes = {
                        viewModel.deleteAdvertisement(advertisement!!.id)
                        bottomSheetFragmentLogOut.dismiss()
                    }
                }
                R.id.favouriteFragment -> {

                }
                else -> {

                }
            }
        }

        setAllInfoInUI()

        setupObservers()

        setUpBanner()

        binding.bCallToUser.setOnClickListener {
            callToOwner("+998990362607")
        }
    }


    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateDeleteA.collect {
                    when (it) {
                        is UiStateObject.LOADING -> {}
                        is UiStateObject.SUCCESS -> {
                            Toast.makeText(
                                requireContext(),
                                "This Advertisement has been Successfully deleted!!",
                                Toast.LENGTH_SHORT
                            ).show()
                            requireActivity().onBackPressed()
                        }
                        is UiStateObject.ERROR -> {
                            Log.d(TAG, "Error deleteA:" + it.message)
                        }
                        else -> Unit
                    }
                }
            }
        }

    }


    private fun setAllInfoInUI(){
        when (findNavController().previousBackStackEntry!!.destination.id) {
            R.id.myAddsFragment -> {
                binding.ivFavoriteDelete.setImageResource(R.drawable.ic_delete)
            }
            R.id.favouriteFragment -> {
                binding.ivFavoriteDelete.setImageResource(R.drawable.ic_like)
            }
            else -> {
                binding.ivFavoriteDelete.setImageResource(R.drawable.ic_favorite)
            }
        }

        binding.tvCarName.text = advertisement!!.transport.model.name
        if (advertisement!!.prices.size < 2){
            if (advertisement!!.prices[0].type == Type.MONTHLY) binding.tvCarsCostMonth.text = advertisement!!.prices[0].quantity.toString()
            else binding.tvCarsCostDay.text = advertisement!!.prices[0].quantity.toString()
        }else{
            binding.tvCarsCostDay.text = advertisement!!.prices[0].quantity.toString()
            binding.tvCarsCostMonth.text = advertisement!!.prices[1].quantity.toString()
        }
        binding.tvDescription.text = advertisement!!.description
        if (advertisement!!.minDuration >= 30){
            binding.tvMinLimit.text = (advertisement!!.minDuration / 30).toString()
            binding.tvMinType.text = getString(R.string.str_months)
        }else{
            binding.tvMinLimit.text = advertisement!!.minDuration.toString()
            binding.tvMinType.text = getString(R.string.str_days)
        }
        binding.tvLocation.text = advertisement!!.location.name
        binding.tvColorName.text = advertisement!!.transport.color
        binding.tvManagementSystem.text = advertisement!!.transport.transmission.toString()
        binding.tvFuel.text = advertisement!!.transport.fuelType.toString()
        binding.tvYear.text = advertisement!!.transport.year.toString()
        if (advertisement!!.transport.wellEquipped){
            binding.llLineConditioner.visibility = View.VISIBLE
            binding.llConditionerDetails.visibility = View.VISIBLE
        } else {
            binding.llLineConditioner.visibility = View.GONE
            binding.llConditionerDetails.visibility = View.GONE
        }
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

    private fun callToOwner(phone: String){
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:$phone") //change the number

        Dexter.withContext(requireContext())
            .withPermission(Manifest.permission.CALL_PHONE)
            .withListener(object: PermissionListener{
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                    startActivity(callIntent)
                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest?,
                    p1: PermissionToken?
                ) {
                    p1!!.continuePermissionRequest()
                }

            }).check()
    }

    private fun getModelID() {
        setFragmentResultListener("from_favourites"){key,bundle->
            val result = bundle.getString("data")
            //result is the modelname of the car from SearchFragment
            Log.d("OnRsultFragment", "onCreate: $result")
            Toast.makeText(requireContext(), "$result", Toast.LENGTH_SHORT).show()
        }
    }

}