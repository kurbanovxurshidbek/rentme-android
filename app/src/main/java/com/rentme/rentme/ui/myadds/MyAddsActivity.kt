package com.rentme.rentme.ui.myadds

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.rentme.rentme.R
import com.rentme.rentme.adapter.MyAddAdapter
import com.rentme.rentme.databinding.ActivityMyAddsBinding
import com.rentme.rentme.model.Result
import com.rentme.rentme.ui.details.DetailsActivity


class MyAddsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyAddsBinding
    private val adapter by lazy { MyAddAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyAddsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

    }

    private fun initViews() {
        binding.ivBack.setOnClickListener { finish() }
        getAllResult()

        adapter.onClick = {result ->
            Intent(this, DetailsActivity::class.java).also {
                it.putExtra("carName", result.carName)
                startActivity(it)
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
        items.add(Result(R.drawable.im_malibu,"Malibu 2","","200$"))
        items.add(Result(R.drawable.im_tesla_model3,"Model 3","","350$"))
        items.add(Result(R.drawable.im_mersades,"AMG","","400$"))
        items.add(Result(R.drawable.im_tesla_model3,"Model 3","","300$"))
        items.add(Result(R.drawable.im_mersades,"AMG 2","","350$"))
        adapter.submitData(items)

        binding.rvMyAdds.layoutManager = GridLayoutManager(this, 1)
        binding.rvMyAdds.adapter = adapter
    }

}