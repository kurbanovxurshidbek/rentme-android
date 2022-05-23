package com.rentme.rentme.ui.filter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.rentme.rentme.R
import com.rentme.rentme.databinding.ActivityFiltersBinding

class FiltersActivity : AppCompatActivity() {

    private lateinit var binding:ActivityFiltersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFiltersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSpinnerType()
        initSpinnerModel()
        initSpinnerYear()

        initView()
    }

    private fun initView() {
        binding.ivBackToDetails.setOnClickListener {
            finish()
        }
    }

    private fun initSpinnerType() {
        val adapter = ArrayAdapter.createFromResource(this,
            R.array.cars_type,
            android.R.layout.simple_spinner_item
        )

        binding.spnCars.adapter = adapter
        binding.spnCars.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
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

        binding.spnCarModel.adapter = ArrayAdapter<String>(this, R.layout.spinner_item_view, models)
        binding.spnCarModel.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedItem = p0!!.getItemAtPosition(p2)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun initSpinnerYear(){
        val adapter = ArrayAdapter.createFromResource(this,
            R.array.cars_year,
            android.R.layout.simple_spinner_item
        )

        binding.spnYear.adapter = adapter
        binding.spnCarModel.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedItem = p0!!.getItemAtPosition(p2)
//                Toast.makeText(this@FiltersActivity, "$selectedItem Selected", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }
}
