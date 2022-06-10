package com.rentme.rentme.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rentme.rentme.R
import com.rentme.rentme.databinding.ItemFilterModelYearBinding
import com.rentme.rentme.databinding.ItemResultLayoutBinding
import com.rentme.rentme.model.Car
import com.rentme.rentme.model.Result

class FilterModelYearAdapter:RecyclerView.Adapter<FilterModelYearAdapter.FilterModelYearViewHolder>() {
    private val dif = AsyncListDiffer(this,ITEM_DIF)
    private var clickedPosition: Int = -1
    var onClick:((Car) -> Unit)? = null

    inner class FilterModelYearViewHolder(private val binding:ItemFilterModelYearBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(){
                val car = dif.currentList[adapterPosition]
                binding.apply {
                    tvModelYear.text = car.modelYera.toString()
                    if (adapterPosition == clickedPosition) root.setBackgroundResource(R.drawable.filter_rounded_chacked_corner)
                    else root.setBackgroundResource(R.drawable.filter_rounded_corner)
                    root.setOnClickListener {
                        onClick?.invoke(car)
                        clickedPosition = adapterPosition
                        notifyDataSetChanged()
                    }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):FilterModelYearViewHolder{
        return FilterModelYearViewHolder(
            ItemFilterModelYearBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: FilterModelYearViewHolder, position: Int) = holder.bind()

    override fun getItemCount(): Int = dif.currentList.size

    fun submitData(list: List<Car>){
        dif.submitList(list)
    }

    companion object {
        private val ITEM_DIF = object : DiffUtil.ItemCallback<Car>() {

            override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean =
                oldItem.modelYera == newItem.modelYera

            override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean =
                oldItem == newItem
        }
    }

}