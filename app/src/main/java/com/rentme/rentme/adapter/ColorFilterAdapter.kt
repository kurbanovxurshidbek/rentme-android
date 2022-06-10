package com.rentme.rentme.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rentme.rentme.R
import com.rentme.rentme.databinding.ItemFilterCarColorsBinding

class ColorFilterAdapter:RecyclerView.Adapter<ColorFilterAdapter.ColorFilterViewHolder>() {
    private val dif = AsyncListDiffer(this, ColorFilterAdapter.ITEM_DIF)
    private var clickedPosition: Int = -1
    var onClick: ((Int) -> Unit)? = null

    inner class ColorFilterViewHolder(private val binding: ItemFilterCarColorsBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(){
            val colorCars = dif.currentList[adapterPosition]
            binding.apply {
                ivCarsColor.setBackgroundResource(colorCars)
                if (adapterPosition == clickedPosition) root.setBackgroundResource(R.drawable.shape_circle_rounded_border)
                else root.setBackgroundResource(R.drawable.shape_circle_unchecked_color)
                root.setOnClickListener {
                    onClick?.invoke(colorCars)
                    clickedPosition = adapterPosition
                    notifyDataSetChanged()
                }

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

    fun submitData(list: List<Int>){
        dif.submitList(list)
    }

    companion object {
        private val ITEM_DIF = object : DiffUtil.ItemCallback<Int>() {

            override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean = true

            override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean = true
        }
    }
}