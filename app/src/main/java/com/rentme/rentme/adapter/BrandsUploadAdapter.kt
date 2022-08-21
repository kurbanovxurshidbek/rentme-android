package com.rentme.rentme.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rentme.rentme.R
import com.rentme.rentme.data.local.entity.BrandListEntity
import com.rentme.rentme.databinding.ItemFilterBrandsBinding


class BrandsUploadAdapter : RecyclerView.Adapter<BrandsUploadAdapter.BrandUViewHolder>() {
    private val dif = AsyncListDiffer(this, ITEM_DIF)
    private var clickedPosition: Int = -1
    var onClick: ((BrandListEntity?) -> Unit)? = null

    inner class BrandUViewHolder(private val binding: ItemFilterBrandsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind() {
            val result = dif.currentList[adapterPosition]
            binding.apply {
                Glide.with(root)
                    .load(result.image)
                    .error(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(ivBrand)
                tvBrandName.text = result.id

                if (adapterPosition == clickedPosition) flBackground.setBackgroundResource(R.drawable.shape_circle_rounded_border)
                else flBackground.setBackgroundResource(R.drawable.circler_white_border)

                root.setOnClickListener {
                    if (result.models.isNotEmpty()){
                        onClick?.invoke(result)
                        clickedPosition = adapterPosition
                        notifyDataSetChanged()
                    }else{
                        onClick?.invoke(null)
                    }
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandUViewHolder {
        return BrandUViewHolder(
            ItemFilterBrandsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BrandUViewHolder, position: Int) = holder.bind()

    override fun getItemCount(): Int = dif.currentList.size

    fun submitData(list: List<BrandListEntity>) {
        dif.submitList(list)
    }

    companion object {
        private val ITEM_DIF = object : DiffUtil.ItemCallback<BrandListEntity>() {
            override fun areItemsTheSame(oldItem: BrandListEntity, newItem: BrandListEntity): Boolean = true
            override fun areContentsTheSame(oldItem: BrandListEntity, newItem: BrandListEntity): Boolean = true
        }
    }

}