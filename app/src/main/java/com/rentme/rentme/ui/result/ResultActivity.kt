package com.rentme.rentme.ui.result

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.rentme.rentme.R
import com.rentme.rentme.adapter.ResultAdapter
import com.rentme.rentme.databinding.ActivityResultBinding
import com.rentme.rentme.model.Result
import com.rentme.rentme.ui.details.DetailsActivity
import com.rentme.rentme.ui.filter.FiltersActivity

class ResultActivity : AppCompatActivity() {

    private lateinit var binding:ActivityResultBinding
    private val adapter by lazy { ResultAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        getAllResult()
    }

    private fun initViews() {
        binding.rvResult.layoutManager = GridLayoutManager(this,1)
        binding.rvResult.adapter = adapter
        adapter.onClick = {result ->
            Intent(this,DetailsActivity::class.java).also {
                it.putExtra("carName", result.carName)
                startActivity(it)
            }
        }

        binding.apply {
            llFilter.setOnClickListener {
                intent = Intent(this@ResultActivity,FiltersActivity::class.java)
                startActivity(intent)
            }


            icBackToTypes.setOnClickListener {
                finish()
            }
        }
    }

    private fun getAllResult() {
        val items:ArrayList<Result> = ArrayList()
        items.add(Result(R.drawable.im_malibu,"Malibu 2","","200$",))
        items.add(Result(R.drawable.im_tesla_model3,"Model 3","","350$",))
        items.add(Result(R.drawable.im_mersades,"AMG","","400$",))
        items.add(Result(R.drawable.im_tesla_model3,"Model 3","","300$",))
        items.add(Result(R.drawable.im_mersades,"AMG 2","","350$",))

        adapter.submitData(items)
    }
}
