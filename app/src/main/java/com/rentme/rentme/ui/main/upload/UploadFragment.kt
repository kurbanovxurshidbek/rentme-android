package com.rentme.rentme.ui.main.upload

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.rentme.rentme.R
import com.rentme.rentme.databinding.FragmentUploadBinding
import com.rentme.rentme.model.Location
import com.rentme.rentme.model.Price
import com.rentme.rentme.model.UploadAdvertisement
import kotlin.collections.ArrayList

class UploadFragment : Fragment() {
    private var _binding: FragmentUploadBinding? = null
    private val binding get() = _binding!!
    private var minTimeHelper = 0
    private var maxTimeHelper = 0
    private var carCategory = ""
    private lateinit var location: Location

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUploadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectTypeSpinner()
        calendarView()
        selectLifeTimeChanged()

        binding.btnNext.setOnClickListener { openFeatureFragment() }

        binding.llLocation.setOnClickListener {
            selectLocationFragment()
        }
        fragmentManager?.setFragmentResultListener("locationResult", viewLifecycleOwner) { requestKey, result ->
            binding.tvLocation.text = result.getString("location", "Select Location")
            location = Location(binding.tvLocation.text.toString(), 0.0, 0.0)
        }

    }


    @SuppressLint("SimpleDateFormat")
    private fun openFeatureFragment() {
        if (!binding.tvDate.text.equals(getString(R.string.str_select_start_date))
            && !binding.tvLocation.text.equals(getString(R.string.str_select_location))
            && binding.minCountTime.text.isNotEmpty()
            && binding.maxCountTime.text.isNotEmpty()
        ) {
            if (minTimeHelper < maxTimeHelper){
                val uploadAdvertisement = UploadAdvertisement(
                    null, null, carCategory, location, binding.tvDate.text.toString(),
                    minTimeHelper.toLong(), maxTimeHelper.toLong(), null
                )
                findNavController().navigate(R.id.featureFragment,
                    bundleOf("uploadAdvertisement" to Gson().toJson(uploadAdvertisement)))
            }
        }else{
            Toast.makeText(requireContext(), getString(R.string.str_fill_all_fields), Toast.LENGTH_SHORT).show()
        }
    }

    private fun selectLocationFragment() {
        findNavController().navigate(R.id.selectLocationFragment)
    }

    private fun selectLifeTimeChanged() {
        binding.maxRadioGroup.setOnCheckedChangeListener { p0, checkedId ->
            binding.maxCountTime.isClickable = true
            binding.maxCountTime.isCursorVisible = true
            when (checkedId) {
                R.id.max_radio_day -> {
                    binding.maxTypeTime.text = getString(R.string.str_day)
                    if (binding.maxCountTime.text.isNotEmpty()) maxTimeHelper =
                        binding.maxCountTime.text.toString().toInt()
                    if (maxTimeHelper < minTimeHelper){
                        binding.llMaxLifetime.setBackgroundResource(R.drawable.ll_background_red_rounded)
                        binding.maxCountTime.setTextColor(resources.getColor(R.color.error_text_color))
                    }
                }
                else -> {
                    binding.maxTypeTime.text = getString(R.string.str_month)
                    if (binding.maxCountTime.text.isNotEmpty()) maxTimeHelper =
                        binding.maxCountTime.text.toString().toInt() * 30
                }
            }
            if (maxTimeHelper > minTimeHelper) {
                binding.llMaxLifetime.setBackgroundResource(R.drawable.ll_background_date_month)
                binding.llMinLifetime.setBackgroundResource(R.drawable.ll_background_date_month)
                binding.maxCountTime.setTextColor(resources.getColor(R.color.second_text_color))
                binding.minCountTime.setTextColor(resources.getColor(R.color.second_text_color))
            }
        }

        binding.minRadioGroup.setOnCheckedChangeListener { p0, checkedId ->
            binding.minCountTime.isClickable = true
            binding.minCountTime.isCursorVisible = true
            when (checkedId) {
                R.id.min_radio_day -> {
                    binding.minTypeTime.text = getString(R.string.str_day)
                    binding.maxRadioDay.isClickable = true
                    if (binding.minCountTime.text.isNotEmpty()) minTimeHelper =
                        binding.minCountTime.text.toString().toInt()
                }
                else -> {
                    binding.minTypeTime.text = getString(R.string.str_month)
                    binding.maxRadioMonth.isChecked = true
                    binding.maxRadioDay.isClickable = false
                    if (binding.minCountTime.text.isNotEmpty()) minTimeHelper =
                        binding.minCountTime.text.toString().toInt() * 30
                    if (maxTimeHelper < minTimeHelper){
                        binding.llMinLifetime.setBackgroundResource(R.drawable.ll_background_red_rounded)
                        binding.minCountTime.setTextColor(resources.getColor(R.color.error_text_color))
                    }
                }

            }
            if (maxTimeHelper > minTimeHelper) {
                binding.llMinLifetime.setBackgroundResource(R.drawable.ll_background_date_month)
                binding.llMaxLifetime.setBackgroundResource(R.drawable.ll_background_date_month)
                binding.maxCountTime.setTextColor(resources.getColor(R.color.second_text_color))
                binding.minCountTime.setTextColor(resources.getColor(R.color.second_text_color))
            }
        }

        binding.minCountTime.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().isNotEmpty()) {
                    minTimeHelper =
                        if (binding.minTypeTime.text.toString() == getString(R.string.str_day)
                        ) p0.toString().toInt()
                        else (p0.toString().toInt() * 30)
                    if (maxTimeHelper < minTimeHelper){
                        binding.llMinLifetime.setBackgroundResource(R.drawable.ll_background_red_rounded)
                        binding.minCountTime.setTextColor(resources.getColor(R.color.error_text_color))
                    }
                    else {
                        binding.llMinLifetime.setBackgroundResource(R.drawable.ll_background_date_month)
                        binding.llMaxLifetime.setBackgroundResource(R.drawable.ll_background_date_month)
                        binding.maxCountTime.setTextColor(resources.getColor(R.color.second_text_color))
                        binding.minCountTime.setTextColor(resources.getColor(R.color.second_text_color))
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.maxCountTime.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().isNotEmpty()) {
                    maxTimeHelper =
                        if (binding.maxTypeTime.text.toString() == getString(R.string.str_day)
                        ) p0.toString().toInt()
                        else (p0.toString().toInt() * 30)
                    if (maxTimeHelper < minTimeHelper){
                        binding.llMaxLifetime.setBackgroundResource(R.drawable.ll_background_red_rounded)
                        binding.minCountTime.setTextColor(resources.getColor(R.color.error_text_color))
                    }
                    else {
                        binding.llMinLifetime.setBackgroundResource(R.drawable.ll_background_date_month)
                        binding.llMaxLifetime.setBackgroundResource(R.drawable.ll_background_date_month)
                        binding.maxCountTime.setTextColor(resources.getColor(R.color.second_text_color))
                        binding.minCountTime.setTextColor(resources.getColor(R.color.second_text_color))
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

    }

    private fun calendarView() {
        binding.apply {
            ivCalendar.setOnClickListener {
                // create new instance of DatePickerFragment
                val datePickerFragment = DatePickerFragment()
                val supportFragmentManager = requireActivity().supportFragmentManager

                // we have to implement setFragmentResultListener
                supportFragmentManager.setFragmentResultListener(
                    "REQUEST_KEY",
                    viewLifecycleOwner
                ) { resultKey, bundle ->
                    if (resultKey == "REQUEST_KEY") {
                        val date = bundle.getString("SELECTED_DATE")
                        tvDate.text = date
                    }
                }

                // show
                datePickerFragment.show(supportFragmentManager, "DatePickerFragment")
            }
        }
    }

    private fun selectTypeSpinner() {
        val types: ArrayList<String> = ArrayList()
        types.add("Sedan")
        types.add("Truck")

        binding.spnType.adapter =
            ArrayAdapter<String>(requireContext(), R.layout.spinner_item_view, types)
        binding.spnType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                carCategory = p0!!.getItemAtPosition(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

    }


}