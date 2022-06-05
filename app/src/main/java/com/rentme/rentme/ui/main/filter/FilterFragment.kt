package com.rentme.rentme.ui.main.filter

import android.os.Bundle
import android.view.DragEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.slider.RangeSlider
import com.google.android.material.slider.Slider
import com.rentme.rentme.R
import com.rentme.rentme.adapter.ColorFilterAdapter
import com.rentme.rentme.adapter.FilterModelYearAdapter
import com.rentme.rentme.databinding.FragmentFilterBinding
import com.rentme.rentme.model.Car
import com.rentme.rentme.ui.upload.DatePickerFragment

class FilterFragment : Fragment() {

    private var _binding: FragmentFilterBinding? = null
    private val adapter by lazy { ColorFilterAdapter() }
    private val adapter_cy by lazy { FilterModelYearAdapter() }
    private var color = 0


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

        initSpinnerType()
        initSpinnerModel()

        initView()
        //calendarView()
        dataRangePickerView()

        binding.btnResult.setOnClickListener{
            findNavController().navigate(R.id.resultFragment)
        }
    }

    private fun initView() {
        binding.rvColors.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvColors.adapter = adapter
        getAllColors()

        binding.rvModelYear.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvModelYear.adapter = adapter_cy
        getAllModelYear()

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

//    private fun calendarView(){
//        binding.apply {
//            llFilterCalendar.setOnClickListener {
//                val datePickerFragment = DatePickerFragment()
//                val supportFragmentManager = requireActivity().supportFragmentManager
//
//                supportFragmentManager.setFragmentResultListener(
//                    "REQUEST_KEY",
//                    viewLifecycleOwner
//                )
//                { resultKey, bundle ->
//                    if (resultKey == "REQUEST_KEY") {
//                        val date = bundle.getString("SELECTED_DATE")
//                        tvStartDate.text = date
//                    }
//                }
//
//                // show
//                datePickerFragment.show(supportFragmentManager, "DatePickerFragment")
//            }
//        }
//    }

    private fun dataRangePickerView(){
        binding.apply {
            llFilterCalendar.setOnClickListener {
                val dataRangePickerFragment = DataRangePickerFragment()
                val supportFragmentManager = requireActivity().supportFragmentManager

                val datePicker = MaterialDatePicker.Builder.dateRangePicker().build()
                datePicker.show(supportFragmentManager, "DatePicker")

                // Setting up the event for when ok is clicked
                datePicker.addOnPositiveButtonClickListener {
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

    private fun getAllColors() {
        val items:ArrayList<String> = ArrayList()
        items.add(String())
        items.add(String())
        items.add(String())
        items.add(String())
        items.add(String())

        adapter.submitData(items)
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

    private fun initSpinnerType() {
        val adapter = ArrayAdapter.createFromResource(requireContext(),
            R.array.cars_type,
            android.R.layout.simple_spinner_item
        )

        binding.spnCars.adapter = adapter
        binding.spnCars.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedItem = p0!!.getItemAtPosition(p2)
//                Toast.makeText(this@FiltersActivity, "$selectedItem Selected", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

    }

    private fun initSpinnerModel() {
        val models: ArrayList<String> = ArrayList()
        models.add("Yengil moshina")
        models.add("Yuk moshina")
        models.add("Velesiped")
        models.add("Skutor")

        binding.spnCarModel.adapter = ArrayAdapter<String>(requireContext(), R.layout.spinner_item_view, models)
        binding.spnCarModel.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedItem = p0!!.getItemAtPosition(p2)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }


}