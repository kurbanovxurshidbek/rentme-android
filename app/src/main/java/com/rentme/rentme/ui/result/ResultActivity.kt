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
    }

    private fun getAllResult() {
        val items:ArrayList<Result> = ArrayList()
        items.add(Result(R.drawable.im_malibu,"Malibu 2","","200$","",))
        items.add(Result(R.drawable.im_malibu,"Malibu 3","","250$","",))
        items.add(Result(R.drawable.im_malibu,"Nexia 2","","100$","",))
        items.add(Result(R.drawable.im_malibu,"Damas 2","","50$","",))
        items.add(Result(R.drawable.im_malibu,"Gentra 2","","150$","",))

        adapter.sumbitData(items)
    }
}
