package com.rentme.rentme.ui.types

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rentme.rentme.R
import com.rentme.rentme.adapter.TypesAdapter
import com.rentme.rentme.model.Types

class TypesActivity : AppCompatActivity() {

    lateinit var rv_types:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_types)

        initViews()
    }

    private fun initViews() {
        rv_types = findViewById(R.id.rv_types)
        rv_types.layoutManager = GridLayoutManager(this,1)

        refreshAdapter(getAllTypes())
    }

    fun refreshAdapter(types: ArrayList<Types>){
        val adapter = TypesAdapter(this, types)
        rv_types!!.adapter = adapter
    }

    fun getAllTypes(): ArrayList<Types>{
        val items: ArrayList<Types> = ArrayList<Types>()
        items.add(Types(R.drawable.tesla_car,"Tesla","15"))
        items.add(Types(R.drawable.tesla_car,"GM","10"))
        items.add(Types(R.drawable.tesla_car,"BMW","5"))
        items.add(Types(R.drawable.tesla_car,"Mersades Benz","18"))

        return items
    }
}