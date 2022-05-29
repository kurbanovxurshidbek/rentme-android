package com.rentme.rentme.ui.upload

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import android.widget.*
import com.rentme.rentme.R
import com.rentme.rentme.databinding.FragmentUploadBinding
import java.util.*
import kotlin.collections.ArrayList

class UploadFragment : Fragment() {
    private var _binding: FragmentUploadBinding? = null
    private val binding get() = _binding!!

    //sherzod
    lateinit var tv_date: TextView
    lateinit var iv_calendar: ImageView

    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectTypeSpinner()
        openFeatureActivity()
        calendarView()

        binding.llLocation.setOnClickListener {
            findNavController().navigate(R.id.selectLocationFragment)
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




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openFeatureActivity() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.featureFragment)
        }
    }


    private fun selectTypeSpinner() {
        val types: ArrayList<String> = ArrayList()
        types.add("Yengil moshina")
        types.add("Yuk moshina")
        types.add("Velesiped")
        types.add("Skutor")

        binding.spnType.adapter =
            ArrayAdapter<String>(requireContext(), R.layout.spinner_item_view, types)
        binding.spnType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedItem = p0!!.getItemAtPosition(p2)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

    }


}