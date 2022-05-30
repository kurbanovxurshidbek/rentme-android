package com.rentme.rentme.ui.main.upload

import android.annotation.SuppressLint
import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rentme.rentme.R
import com.rentme.rentme.adapter.CarImageAdapter
import com.rentme.rentme.adapter.ColorAdapter
import com.rentme.rentme.databinding.FragmentFeaturesBinding
import com.sangcomz.fishbun.FishBun
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter


class FeatureFragment : Fragment() {

    private val colorAdapter by lazy { ColorAdapter() }
    private var allPhotos: ArrayList<Uri> = ArrayList()
    private var carImages: ArrayList<Uri> = ArrayList()
    private lateinit var carImageAdapter: CarImageAdapter

    private var _binding: FragmentFeaturesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFeaturesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectModelSpinner()
        selectYearSpinner()
        allColorFunction()

        binding.btnSave.setOnClickListener {
            //findNavController().navigate(R.id.myAddsFragment)
            carImageAdapter.saveCarImageStorage(carImages)
        }
        carImageAdapter = CarImageAdapter()
        binding.rvCarPhotos.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
        binding.rvCarPhotos.adapter = carImageAdapter
        binding.ivAddPhoto.setOnClickListener { pickFishBunCarImages() }

    }

    /**
     * Pick photo using FishBun library
     */
    private fun pickFishBunCarImages(){
        FishBun.with(this)
            .setImageAdapter(GlideAdapter())
            .setMaxCount(10)
            .setMinCount(1)
            .setCamera(true)
            .setSelectedImages(allPhotos)
            .startAlbumWithActivityResultCallback(photoLauncher)
    }

    @SuppressLint("NotifyDataSetChanged")
    private val photoLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){
            allPhotos = it.data?.getParcelableArrayListExtra(FishBun.INTENT_PATH) ?: arrayListOf()
            carImageAdapter.items.addAll(allPhotos)
            carImages.addAll(allPhotos)
            allPhotos.clear()
            carImageAdapter.notifyDataSetChanged()
        }
    }

    private fun allColorFunction(){
        val colors: ArrayList<Int> = ArrayList()
        colors.add(R.color.car_color_1)
        colors.add(R.color.car_color_2)
        colors.add(R.color.car_color_3)
        colors.add(R.color.car_color_4)
        colors.add(R.color.car_color_5)
        colors.add(R.color.car_color_6)
        colors.add(R.color.car_color_7)
        colors.add(R.color.car_color_8)
        colors.add(R.color.car_color_9)
        colors.add(R.color.car_color_10)
        colorAdapter.submitData(colors)

        binding.rvColors.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvColors.adapter = colorAdapter
        colorAdapter.onClick = {color ->
            binding.ivCarColor.setBackgroundResource(color)
        }
    }

    private fun selectModelSpinner(){
        val models: ArrayList<String> = ArrayList()
        models.add("Malibu")
        models.add("Captiva")
        models.add("Nexia")
        models.add("Spark")
        models.add("Matiz")
        models.add("Damas")

        binding.spnModels.adapter = ArrayAdapter<String>(requireContext(), R.layout.spinner_item_view, models)
        binding.spnModels.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedItem = p0!!.getItemAtPosition(p2)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

    }

    private fun selectYearSpinner(){
        val years: ArrayList<String> = ArrayList()
        years.add("2010")
        years.add("2011")
        years.add("2012")
        years.add("2013")
        years.add("2014")
        years.add("2015")
        years.add("2016")
        years.add("2017")
        years.add("2018")
        years.add("2019")
        years.add("2020")
        years.add("2021")
        years.add("2022")

        binding.spnYear.adapter = ArrayAdapter<String>(requireContext(), R.layout.spinner_item_view, years)
        binding.spnYear.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedItem = p0!!.getItemAtPosition(p2)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

    }

}