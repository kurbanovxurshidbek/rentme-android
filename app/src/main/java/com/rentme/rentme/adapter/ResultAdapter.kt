package com.rentme.rentme.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.rentme.rentme.R
import com.rentme.rentme.databinding.ItemResultLayoutBinding
import com.rentme.rentme.model.Result
import com.rentme.rentme.model.Types

class ResultAdapter:RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {
    private val dif = AsyncListDiffer(this,ITEM_DIF)
    var onClick:((Result) -> Unit)? = null

    inner class ResultViewHolder(private val binding:ItemResultLayoutBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(){
                val result = dif.currentList[adapterPosition]
                binding.apply {
                    tvCarsName.text = result.carName
                    tvCarsCostDay.text = result.costDay
                    tvCarsCostWeek.text = result.costWeek
                    tvCarsCostMonth.text = result.costMonth

                    ivCarsImages.setImageResource(result.carImage)

                    root.setOnClickListener{
                        onClick?.invoke(result)
                    }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ResultViewHolder{
        return ResultViewHolder(
            ItemResultLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) = holder.bind()

    override fun getItemCount(): Int = dif.currentList.size

    fun submitData(list: List<Result>){
        dif.submitList(list)
    }

    companion object {
        private val ITEM_DIF = object : DiffUtil.ItemCallback<Result>() {

            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
                oldItem.carName == newItem.carName && oldItem.carImage == newItem.carImage

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
                oldItem == newItem
        }
    }
}