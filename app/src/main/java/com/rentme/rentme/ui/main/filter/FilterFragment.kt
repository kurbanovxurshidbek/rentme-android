package com.rentme.rentme.ui.main.filter

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.gson.Gson
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.rentme.rentme.R
import com.rentme.rentme.adapter.BrandsAdapter
import com.rentme.rentme.adapter.ColorFilterAdapter
import com.rentme.rentme.adapter.FilterModelYearAdapter
import com.rentme.rentme.databinding.FragmentFilterBinding
import com.rentme.rentme.model.Brands
import com.rentme.rentme.model.Car
import com.rentme.rentme.model.FilterPage
import com.rentme.rentme.model.Model
import com.rentme.rentme.utils.SelectColor
import com.rentme.rentme.utils.UiStateObject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FilterFragment : Fragment() {

    private var _binding: FragmentFilterBinding? = null
    private val adapter by lazy { ColorFilterAdapter() }
    private val adapter_cy by lazy { FilterModelYearAdapter() }
    private val brandsAdapter by lazy { BrandsAdapter() }
    private val viewModel: FilterViewModel by viewModels()

    private lateinit var rvMainBrands: RecyclerView

    private var filterPage = FilterPage()
    private var carColors: ArrayList<String>? = null
    private var modelYear: Int = 0

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFilterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initSpinnerModel()

        getAllBrands()

        initView()
        dataRangePickerView()
        setupObservers()

        binding.btnResult.setOnClickListener{
//            findNavController().navigate(R.id.resultFragment)
            openResultPage()
        }

        binding.tvLocation.setOnClickListener {
            selectLocationFragment()
        }

        fragmentManager?.setFragmentResultListener("locationResult", viewLifecycleOwner) { requestKey, result ->
            binding.tvLocation.text = result.getString("location", "Select Location")
        }
    }

    private fun openResultPage() {
        var filterPage = FilterPage(colors = carColors, year = modelYear)
        viewModel.getFilterResult(filterPage)

    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.filterState.collect{
                when(it){
                    is UiStateObject.LOADING -> {
                        //show progress
                    }
                    is UiStateObject.SUCCESS -> {
                        findNavController().navigate(R.id.resultFragment, bundleOf("data" to Gson().toJson(it.data.data)))

                    }
                    is UiStateObject.ERROR -> {
                        //show error
                    }else -> Unit
                }
            }
        }
    }

    private fun initView() {
        carColors = ArrayList<String>()

        binding.rvColors.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvColors.adapter = adapter
        adapter.onClick = {
            carColors!!.add(SelectColor.codeToName(it))
        }
        getAllColors()

        binding.rvModelYear.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvModelYear.adapter = adapter_cy
        adapter_cy.onClick = {
            modelYear = it.modelYera
        }
        getAllModelYear()

        rvMainBrands = binding.rvBrands
        rvMainBrands.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = brandsAdapter
        }

        binding.ivBackToDetails.setOnClickListener {
            requireActivity().onBackPressed()
        }

       // this is slider
        binding.sldCost.addOnChangeListener { slider, value, fromUser ->
            val values = binding.sldCost.values
            binding.tvCostMin.text = values[0].toInt().toString()
            binding.tvCostMax.text = values[1].toInt().toString()
        }
    }


    private fun dataRangePickerView(){
        binding.apply {
            llFilterCalendar.setOnClickListener {
                val dataRangePickerFragment = DataRangePickerFragment()
                val supportFragmentManager = requireActivity().supportFragmentManager

                val datePicker = MaterialDatePicker.Builder.dateRangePicker().build()
                datePicker.show(supportFragmentManager, "DatePicker")

                // Setting up the event for when ok is clicked
                datePicker.addOnPositiveButtonClickListener {
                    binding.tvStartDate.text = datePicker.headerText
                    Toast.makeText(context, "${datePicker.headerText} is selected", Toast.LENGTH_LONG).show()
                }

                // Setting up the event for when cancelled is clicked
                datePicker.addOnNegativeButtonClickListener {
                    Toast.makeText(context, "${datePicker.headerText} is cancelled", Toast.LENGTH_LONG).show()
                }

                // Setting up the event for when back button is pressed
                datePicker.addOnCancelListener {
                    Toast.makeText(context, "Date Picker Cancelled", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun getAllBrands(){
        val brands = ArrayList<Brands>()
        brands.add(Brands(image = "https://www.carlogos.org/car-logos/tesla-logo.png", name = "Tesla", models = arrayListOf(
            Model()
        )))
        brands.add(Brands(image = "https://www.carlogos.org/car-logos/bmw-logo.png", name = "BMW", models = arrayListOf(Model())))
        brands.add(Brands(image = "https://www.carlogos.org/car-logos/ferrari-logo.png", name = "Ferrari", models = arrayListOf(Model())))
        brands.add(Brands(image = "https://www.carlogos.org/car-logos/ford-logo.png", name = "Ford", models = arrayListOf(Model())))
        brands.add(Brands(image = "https://www.carlogos.org/car-logos/porsche-logo.png", name = "Porsche", models = arrayListOf(Model())))
        brands.add(Brands(image = "https://www.carlogos.org/car-logos/lamborghini-logo.png", name = "Lamborghini", models = arrayListOf(Model())))
        brands.add(Brands(image = "https://www.carlogos.org/car-logos/toyota-logo.png", name = "toyota", models = arrayListOf(Model())))

        brandsAdapter.submitData(brands)
    }

    private fun getAllColors() {
        val colors: ArrayList<Int> = ArrayList()
        colors.add(R.color.car_race_blue)
        colors.add(R.color.car_velvet_red)
        colors.add(R.color.car_corrida_red)
        colors.add(R.color.car_yellow)
        colors.add(R.color.car_orange)
        colors.add(R.color.car_black)
        colors.add(R.color.car_meteor_grey)
        colors.add(R.color.car_bright_white)
        colors.add(R.color.car_candy_white)
        colors.add(R.color.car_brilliant_silver)
        colors.add(R.color.car_energy_blue)


        adapter.submitData(colors)

    }

    private fun getAllModelYear(){
        val items: ArrayList<Car> = ArrayList()
        items.add(Car(2022,""))
        items.add(Car(2021,""))
        items.add(Car(2020,""))
        items.add(Car(2019,""))
        items.add(Car(2018,""))
        items.add(Car(2017,""))
        items.add(Car(2016,""))
        items.add(Car(2015,""))
        items.add(Car(2014,""))
        items.add(Car(2013,""))

        adapter_cy.submitData(items)
    }

    private fun initSpinnerModel() {
        val models: ArrayList<String> = ArrayList()
        models.add("Nexia")
        models.add("Malibu")
        models.add("Velesiped")
        models.add("Skutor")

        binding.spnCarModel.adapter = ArrayAdapter<String>(requireContext(), R.layout.spinner_item_view, models)
        binding.spnCarModel.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedItem = p0!!.getItemAtPosition(p2)
                filterPage.model = selectedItem.toString()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun getResult(){

    }


    private fun selectLocationFragment() {
//        checkPermission()
        Dexter.withContext(requireContext())
            .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                    findNavController().navigate(R.id.mapFragment)
                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                    if (p0!!.isPermanentlyDenied) {
                        val dialog = AlertDialog.Builder(requireActivity()).apply {
                            setTitle("Permission Denied")
                            setMessage("Permission to access device location is permanently denied. You need to go to setting to allow to the permission.")
                            setNegativeButton("Cancel", null)
                            setPositiveButton(
                                "Ok"
                            ) { dialog, which ->
//                                val intent = Intent().apply {
//                                    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
//                                    data = Uri.fromParts("package", requireActivity().packageName, null)
//                                }
                            }
                        }
                        dialog.create().show()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Permission denied",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest?,
                    p1: PermissionToken?
                ) {
                    p1!!.continuePermissionRequest()
                }

            }).check()
    }

}