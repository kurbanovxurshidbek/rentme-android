package com.rentme.rentme.ui.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.rentme.rentme.R

class ResultActivity : AppCompatActivity() {

    lateinit var rv_result:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        initViews()
    }

    private fun initViews() {
        
    }
}