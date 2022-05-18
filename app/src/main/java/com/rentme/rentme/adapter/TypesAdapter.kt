package com.rentme.rentme.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rentme.rentme.R
import com.rentme.rentme.model.Types

class TypesAdapter(var context: Context, var items:ArrayList<Types>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_types_layout, parent, false)
        return TypesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        if (holder is TypesViewHolder){
            var tv_carsName = holder.tv_carsName
            var tv_carNumbers = holder.tv_carNumbers
            var iv_carsImage = holder.iv_cars

            tv_carsName.text = item.carName
            tv_carNumbers.text = item.carsNumber

            iv_carsImage.setImageResource(item.carImage)

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class TypesViewHolder(view: View):RecyclerView.ViewHolder(view){
        var iv_cars: ImageView
        var tv_carsName:TextView
        var tv_carNumbers:TextView

        init {
            iv_cars = view.findViewById(R.id.iv_car_image)
            tv_carsName = view.findViewById(R.id.tv_cars_name)
            tv_carNumbers = view.findViewById(R.id.tv_cars_count)
        }
    }
}