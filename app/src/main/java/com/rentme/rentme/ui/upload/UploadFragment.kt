package com.rentme.rentme.ui.upload

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
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import android.widget.*
import com.rentme.rentme.R
import com.rentme.rentme.databinding.FragmentUploadBinding
import com.rentme.rentme.model.UploadAdvertisement
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList

class UploadFragment : Fragment() {
    private var _binding: FragmentUploadBinding? = null
    private val binding get() = _binding!!
    private var minTimeHelper = 0
    private var maxTimeHelper = 0
    private var carCategory = ""

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
        openFeatureActivity()
        calendarView()
        selectLifeTimeChanged()

        binding.btnNext.setOnClickListener { openFeatureFragment() }

        binding.llLocation.setOnClickListener {
            selectLocationFragment()
        }

    }


    @SuppressLint("SimpleDateFormat")
    private fun openFeatureFragment() {
        // !binding.tvLocation.text.equals(getString(R.string.str_select_location))
        if (!binding.tvDate.text.equals(getString(R.string.str_select_start_date))
            && binding.minCountTime.text.isNotEmpty()
            && binding.maxCountTime.text.isNotEmpty()
        ) {
            if (minTimeHelper < maxTimeHelper){
                val uploadAdvertisement = UploadAdvertisement(
                    null, null, carCategory, null, binding.tvDate.text.toString(),
                    minTimeHelper.toLong(), maxTimeHelper.toLong(), null
                )
                findNavController().navigate(R.id.action_uploadFragment_to_featureFragment,
                    bundleOf("uploadAdvertisement" to uploadAdvertisement))
            }
        }else{
            Toast.makeText(requireContext(), getString(R.string.str_fill_all_fields), Toast.LENGTH_SHORT).show()
        }
    }

    private fun selectLocationFragment() {
        findNavController().navigate(R.id.selectLocationFragment)
    }

//        Log.d("args", arguments?.getString("location", "")!!)
        if (!arguments?.getString("location", "").isNullOrEmpty()) {
            Log.d("args", "--" + arguments?.getString("location", "")!!)
            binding.tvLocation.text = arguments?.getString("location", "")
            findNavController().clearBackStack(R.id.mapsFragment)

            findNavController().navigateUp()
        }
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