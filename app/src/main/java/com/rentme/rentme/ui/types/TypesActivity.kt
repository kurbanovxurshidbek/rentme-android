package com.rentme.rentme.ui.types

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rentme.rentme.R
import com.rentme.rentme.adapter.TypesAdapter
import com.rentme.rentme.databinding.ActivityTypesBinding
import com.rentme.rentme.model.Types
import java.lang.reflect.Type

class TypesActivity : AppCompatActivity() {

    private lateinit var binding:ActivityTypesBinding
    private lateinit var adapter: TypesAdapter
    private var typesList: ArrayList<Types> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTypesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        getAllTypes()
    }

    private fun initViews() {
        adapter = TypesAdapter(this, typesList)
        binding.rvTypes.layoutManager = GridLayoutManager(this,1)
        binding.rvTypes.adapter = adapter
    }

    private fun getAllTypes(){
        val items: ArrayList<Types> = ArrayList()
        items.add(Types(R.drawable.img,"Tesla","15"))
        items.add(Types(R.drawable.img,"GM","10"))
        items.add(Types(R.drawable.img,"BMW","5"))
        items.add(Types(R.drawable.img,"Mersades Benz","18"))

        typesList.addAll(items)
        adapter.notifyDataSetChanged()
    }
}