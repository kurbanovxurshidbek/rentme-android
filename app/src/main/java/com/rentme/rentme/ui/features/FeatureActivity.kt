package com.rentme.rentme.ui.features

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rentme.rentme.R
import com.rentme.rentme.adapter.ColorAdapter
import com.rentme.rentme.adapter.TypesAdapter
import com.rentme.rentme.databinding.ActivityFeaturesBinding
import com.rentme.rentme.ui.location.SelectLocationActivity
import com.rentme.rentme.ui.myadds.MyAddsActivity

class FeatureActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeaturesBinding
    private val colorAdapter by lazy { ColorAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeaturesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

    }

    private fun initViews() {
        selectModelSpinner()
        selectYearSpinner()
        allColorFunction()
        binding.ivBack.setOnClickListener { finish() }

        binding.btnSave.setOnClickListener {
            val intent = Intent(this, MyAddsActivity::class.java)
            startActivity(intent)
            finish()
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

        binding.rvColors.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
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