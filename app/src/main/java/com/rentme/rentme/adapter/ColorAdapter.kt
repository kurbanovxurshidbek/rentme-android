package com.rentme.rentme.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rentme.rentme.databinding.ItemUploadColorViewBinding

class ColorAdapter:RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {
    private val dif = AsyncListDiffer(this,ITEM_DIF)
    var onClick:((Int) -> Unit)? = null

    inner class ColorViewHolder(private val binding:ItemUploadColorViewBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(){
                val result = dif.currentList[adapterPosition]
                Log.d("Colors", "bind: $result")
                binding.apply {
                    ivColor.setBackgroundResource(result)
                    root.setOnClickListener{
                        onClick?.invoke(result)
                    }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ColorViewHolder{
        return ColorViewHolder(
            ItemUploadColorViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) = holder.bind()

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