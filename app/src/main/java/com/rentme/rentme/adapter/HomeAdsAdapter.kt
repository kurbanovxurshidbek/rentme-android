package com.rentme.rentme.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.AsyncListUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rentme.rentme.databinding.ItemMainAdsBinding

class HomeAdsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val dif = AsyncListDiffer(this, ITEM_DIF)

    companion object{
        private val ITEM_DIF = object : DiffUtil.ItemCallback<Int>(){
            override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AdsVH(ItemMainAdsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AdsVH){
            holder.bind()
        }
    }

    override fun getItemCount(): Int = dif.currentList.size

    fun submitData(items: List<Int>){
        dif.submitList(items)
    }

    inner class AdsVH(private val binding: ItemMainAdsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(){
            val ad = dif.currentList[adapterPosition]
            binding.apply {
                ivAds.setImageResource(ad)
            }
        }
    }
}