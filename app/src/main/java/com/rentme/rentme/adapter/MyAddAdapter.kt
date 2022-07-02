package com.rentme.rentme.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rentme.rentme.R
import com.rentme.rentme.databinding.ItemMyUploadViewNewBinding
import com.rentme.rentme.model.Type
import com.rentme.rentme.model.filtermodel.Advertisement


class MyAddAdapter : RecyclerView.Adapter<MyAddAdapter.MyAddViewHolder>() {
    val dif = AsyncListDiffer(this, ITEM_DIF)
    var onClick: ((Advertisement) -> Unit)? = null
    var deleteA: ((Int) -> Unit)? = null

    inner class MyAddViewHolder(private val binding: ItemMyUploadViewNewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val result = dif.currentList[adapterPosition]
            binding.apply {
                tvCarName.text = result.transport!!.model.name
                if (result.prices!!.size < 2) {
                    tvLinePrice.visibility = View.GONE
                    if (result.prices[0].type == Type.DAILY) {
                        tvPriceDaily.text = result.prices[0].quantity.toString()
                        llPriceMonthly.visibility = View.GONE
                    } else {
                        tvPriceMonthly.text = result.prices[0].quantity.toString()
                        llPriceDaily.visibility = View.GONE
                    }
                } else {
                    tvPriceDaily.text = result.prices[0].quantity.toString()
                    tvPriceMonthly.text = result.prices[1].quantity.toString()
                }

                Glide.with(binding.root)
                    .load(result.transport.pictures[0].path)
                    .into(ivCarsImages)

                root.setOnClickListener {
                    onClick?.invoke(result)
                }

                ivDeleteLike.setOnClickListener {
                    deleteA?.invoke(result.id!!)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAddViewHolder {
        return MyAddViewHolder(
            ItemMyUploadViewNewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyAddViewHolder, position: Int) = holder.bind()

    override fun getItemCount(): Int = dif.currentList.size

    fun submitData(list: List<Advertisement>) {
        dif.submitList(list)
    }

    companion object {
        private val ITEM_DIF = object : DiffUtil.ItemCallback<Advertisement>() {

            override fun areItemsTheSame(oldItem: Advertisement, newItem: Advertisement): Boolean =
                oldItem.id == newItem.id && oldItem.transport == newItem.transport

            override fun areContentsTheSame(
                oldItem: Advertisement,
                newItem: Advertisement
            ): Boolean =
                oldItem == newItem
        }
    }

}