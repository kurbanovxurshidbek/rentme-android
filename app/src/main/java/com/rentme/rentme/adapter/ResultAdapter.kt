package com.rentme.rentme.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.rentme.rentme.R
import com.rentme.rentme.model.Result

class ResultAdapter(var context: Context, var items:ArrayList<Result>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_result_layout, parent, false)
        return FilterViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        if (holder is FilterViewHolder){
            var tv_carsName = holder.tv_carsName
            var iv_profile = holder.iv_profile
            var iv_carsImage = holder.iv_cars
            var tv_profile = holder.tv_profile
            var tv_costDay = holder.tv_costDay
            var tv_costWeek = holder.tv_costWeek
            var tv_costMonth = holder.tv_costMonth

            tv_carsName.text = item.carName
            tv_profile.text = item.profileName
            tv_costDay.text = item.costDay
            tv_costWeek.text = item.costWeek
            tv_costMonth.text = item.costMonth

            iv_carsImage.setImageResource(item.carImage)
            iv_profile.setImageResource(item.profile)

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class FilterViewHolder(view: View):RecyclerView.ViewHolder(view){
        var iv_cars: ImageView
        var tv_carsName:TextView
        var iv_profile:ShapeableImageView
        var tv_profile:TextView
        var tv_costDay:TextView
        var tv_costWeek:TextView
        var tv_costMonth:TextView

        init {
            iv_cars = view.findViewById(R.id.iv_cars_images)
            tv_carsName = view.findViewById(R.id.tv_cars_name)
            iv_profile = view.findViewById(R.id.iv_profile)
            tv_profile = view.findViewById(R.id.tv_profile)
            tv_costDay = view.findViewById(R.id.tv_cars_cost_day)
            tv_costWeek = view.findViewById(R.id.tv_cars_cost_week)
            tv_costMonth = view.findViewById(R.id.tv_cars_cost_month)
        }
    }
}