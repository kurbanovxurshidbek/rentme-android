package com.rentme.rentme.ui.features

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.rentme.rentme.R
import com.rentme.rentme.databinding.ActivityFeaturesBinding

class FeatureActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeaturesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeaturesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

    }

    private fun initViews() {
        selectModelSpinner()
        selectYearSpinner()
    }

    private fun selectModelSpinner(){
        val models: ArrayList<String> = ArrayList()
        models.add("Malibu")
        models.add("Captiva")
        models.add("Nexia")
        models.add("Spark")
        models.add("Matiz")
        models.add("Damas")

        binding.spnModels.adapter = ArrayAdapter<String>(this, R.layout.spinner_item_view, models)
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

        binding.spnYear.adapter = ArrayAdapter<String>(this, R.layout.spinner_item_view, years)
        binding.spnYear.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedItem = p0!!.getItemAtPosition(p2)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

    }

}