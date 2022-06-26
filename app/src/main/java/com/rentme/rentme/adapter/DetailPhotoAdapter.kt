package com.rentme.rentme.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rentme.rentme.databinding.ItemDetailImagesBinding
import com.rentme.rentme.model.filtermodel.Picture

class DetailPhotoAdapter : RecyclerView.Adapter<DetailPhotoAdapter.DetailViewHolder>() {
    private val dif = AsyncListDiffer(this, ITEM_DIF)
    var onClick: ((Picture) -> Unit)? = null

    inner class DetailViewHolder(private val binding: ItemDetailImagesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val detailPhoto = dif.currentList[adapterPosition]
            binding.apply {

                Glide.with(binding.root)
                    .load(detailPhoto.path)
                    .into(ivCarImages)
                root.setOnClickListener {
                    onClick?.invoke(detailPhoto)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            ItemDetailImagesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) = holder.bind()

    override fun getItemCount(): Int = dif.currentList.size

    fun submitData(list: List<Picture>){
        dif.submitList(list)
    }

    companion object {
        private val ITEM_DIF = object : DiffUtil.ItemCallback<Picture>() {

            override fun areItemsTheSame(oldItem: Picture, newItem: Picture): Boolean =
                false

            override fun areContentsTheSame(oldItem: Picture, newItem: Picture): Boolean =
                oldItem == newItem
        }
    }
}