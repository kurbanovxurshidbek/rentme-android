package com.rentme.rentme.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rentme.rentme.R
import com.rentme.rentme.databinding.ItemMainBrandsBinding
import com.rentme.rentme.model.Brands

class BrandsAdapter: RecyclerView.Adapter<BrandsAdapter.BrandsVH>() {

    private val diff = AsyncListDiffer(this, ITEM_DIFF)

    companion object{
        val ITEM_DIFF = object: DiffUtil.ItemCallback<Brands>(){
            override fun areItemsTheSame(oldItem: Brands, newItem: Brands): Boolean {
                return false
            }

            override fun areContentsTheSame(oldItem: Brands, newItem: Brands): Boolean {
                return oldItem == newItem
            }

        }
    }

    fun submitData(items: List<Brands>){
        diff.submitList(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandsVH {
        return BrandsVH(ItemMainBrandsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: BrandsVH, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = diff.currentList.size

    inner class BrandsVH(val itemBinding: ItemMainBrandsBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(){
            val brands = diff.currentList[adapterPosition]

            itemBinding.tvBrandName.text = brands.name
            Glide.with(itemBinding.root)
                .load(brands.image)
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemBinding.ivBrand)
        }
    }


}