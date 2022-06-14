package com.rentme.rentme.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rentme.rentme.databinding.ItemFavouriteLayoutBinding
import com.rentme.rentme.model.FavouriteModel

class FavouriteAdapter:RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {
    private val dif = AsyncListDiffer(this,ITEM_DIF)
    var onClick:((FavouriteModel) -> Unit)? = null
    var unLikeFavourite:(()->Unit)? = null

    inner class FavouriteViewHolder(private val binding:ItemFavouriteLayoutBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(){
                val result = dif.currentList[adapterPosition]
                binding.apply {
                    tvCarsName.text = result.transport?.model?.name
                    tvCarsCostDay.text = result.prices?.first().toString()
                    tvCarsCostMonth.text = if (result.prices?.size!! > 1) result.prices.last().quantity.toString() else ""

                    Glide.with(binding.root)
                        .load(result.transport?.pictures?.first()?.path)
                        .into(ivCarsImages)

                    root.setOnClickListener{
                        onClick?.invoke(result)
                    }

                    if (!binding.likeSelector.isSelected)
                    binding.likeSelector.setOnClickListener {
                        binding.likeSelector.isSelected = true
                        unLikeFavourite?.invoke()

                    }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):FavouriteViewHolder{
        return FavouriteViewHolder(
            ItemFavouriteLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) = holder.bind()

    override fun getItemCount(): Int = dif.currentList.size

    fun submitData(list: List<FavouriteModel>){
        dif.submitList(list)
    }

    companion object {
        private val ITEM_DIF = object : DiffUtil.ItemCallback<FavouriteModel>() {

            override fun areItemsTheSame(oldItem: FavouriteModel, newItem: FavouriteModel): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: FavouriteModel, newItem: FavouriteModel): Boolean =
                oldItem == newItem
        }
    }

}