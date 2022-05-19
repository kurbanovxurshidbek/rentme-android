package com.rentme.rentme.ui.types

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.rentme.rentme.R
import com.rentme.rentme.adapter.TypesAdapter
import com.rentme.rentme.databinding.ActivityTypesBinding
import com.rentme.rentme.model.Types
import com.rentme.rentme.ui.result.ResultActivity

class TypesActivity : AppCompatActivity() {

    private lateinit var binding:ActivityTypesBinding
    private val adapter by lazy { TypesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTypesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        getAllTypes()
    }

    private fun initViews() {
        binding.rvTypes.layoutManager = GridLayoutManager(this,1)
        binding.rvTypes.adapter = adapter
        adapter.onClick = { types ->
            Intent(this,ResultActivity::class.java).also {
                it.putExtra("carName",types.carName)
                startActivity(it)
            }
        }

    }

    private fun getAllTypes(){
        val items: ArrayList<Types> = ArrayList()
        items.add(Types(R.drawable.im_malibu,"Tesla","15"))
        items.add(Types(R.drawable.im_malibu,"GM","10"))
        items.add(Types(R.drawable.im_malibu,"BMW","5"))
        items.add(Types(R.drawable.im_malibu,"Mersades Benz","18"))

        adapter.submitData(items)

    }
}