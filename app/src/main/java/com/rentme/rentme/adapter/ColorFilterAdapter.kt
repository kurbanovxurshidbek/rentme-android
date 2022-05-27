package com.rentme.rentme.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rentme.rentme.databinding.ItemFilterCarColorsBinding

class ColorFilterAdapter:RecyclerView.Adapter<ColorFilterAdapter.ColorFilterViewHolder>() {
    private val dif = AsyncListDiffer(this, ColorFilterAdapter.ITEM_DIF)

    inner class ColorFilterViewHolder(private val binding: ItemFilterCarColorsBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(){
            val colorCars = dif.currentList[adapterPosition]
            binding.apply {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ColorFilterViewHolder{
        return ColorFilterViewHolder(
            ItemFilterCarColorsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ColorFilterViewHolder, position: Int) = holder.bind()

    override fun getItemCount(): Int = dif.currentList.size

    fun submitData(list: List<String>){
        dif.submitList(list)
    }

    companion object {
        private val ITEM_DIF = object : DiffUtil.ItemCallback<String>() {

            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem
        }
    }
}