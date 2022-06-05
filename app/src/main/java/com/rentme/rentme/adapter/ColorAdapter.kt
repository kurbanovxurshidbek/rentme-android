package com.rentme.rentme.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rentme.rentme.R
import com.rentme.rentme.databinding.ItemUploadColorViewBinding

class ColorAdapter : RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {
    private val dif = AsyncListDiffer(this, ITEM_DIF)
    private var clickedPosition: Int = -1
    var onClick: ((Int) -> Unit)? = null

    inner class ColorViewHolder(private val binding: ItemUploadColorViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind() {
            val result = dif.currentList[adapterPosition]
            binding.apply {
                ivColor.setBackgroundResource(result)
                if (adapterPosition == clickedPosition) root.setBackgroundResource(R.drawable.shape_circle_rounded_border)
                else root.setBackgroundResource(R.drawable.shape_circle_unchecked_color)
                root.setOnClickListener {
                    onClick?.invoke(result)
                    clickedPosition = adapterPosition
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        return ColorViewHolder(
            ItemUploadColorViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) = holder.bind()

    override fun getItemCount(): Int = dif.currentList.size

    fun submitData(list: List<Int>) {
        dif.submitList(list)
    }

    companion object {
        private val ITEM_DIF = object : DiffUtil.ItemCallback<Int>() {
            override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean = true
            override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean = true
        }
    }

}