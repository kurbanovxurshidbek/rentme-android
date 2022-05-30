package com.rentme.rentme.ui.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rentme.rentme.R
import com.rentme.rentme.adapter.ColorFilterAdapter
import com.rentme.rentme.databinding.FragmentFilterBinding

class FilterFragment : Fragment() {

    private var _binding: FragmentFilterBinding? = null
    private val adapter by lazy { ColorFilterAdapter() }
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
        initSpinnerYear()

        initView()

        binding.btnResult.setOnClickListener{
            findNavController().navigate(R.id.resultFragment)
        }
    }

    private fun initView() {
        binding.rvColors.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvColors.adapter = adapter
        getAllColors()

        binding.ivBackToDetails.setOnClickListener {
            requireActivity().onBackPressed()
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

    private fun initSpinnerYear(){
        val adapter = ArrayAdapter.createFromResource(requireContext(),
            R.array.cars_year,
            android.R.layout.simple_spinner_item
        )

        binding.spnYear.adapter = adapter
        binding.spnCarModel.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedItem = p0!!.getItemAtPosition(p2)
//                Toast.makeText(this@FiltersActivity, "$selectedItem Selected", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

}