package com.rentme.rentme.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rentme.rentme.databinding.ItemSubMainFooterBinding
import com.rentme.rentme.databinding.ItemSubMainVehiclesBinding
import com.rentme.rentme.model.Result

class SubMainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val ITEM_VIEW = 0
    private val ITEM_FOOTER = 1

    private val dif = AsyncListDiffer(this, ITEM_DIF)
    var onClick: ((Result) -> Unit)? = null

    companion object {
        private val ITEM_DIF = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.carName == newItem.carName && oldItem.carImage == newItem.carImage
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun getItemViewType(position: Int) =
        if (position == dif.currentList.size - 1)
            ITEM_FOOTER
        else ITEM_VIEW


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ITEM_FOOTER){
            val itemBinding = ItemSubMainFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return SubMainFooterVH(itemBinding)
        }
        val itemBinding = ItemSubMainVehiclesBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return SubMainVH(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SubMainVH){
            holder.bind()
        }
        if (holder is SubMainFooterVH){

        }
    }

    override fun getItemCount(): Int = dif.currentList.size

    fun submitData(list: List<Result>) {
        dif.submitList(list)
    }

    inner class SubMainVH(private val binding: ItemSubMainVehiclesBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(){
                val result = dif.currentList[adapterPosition]
                binding.apply {
                    tvCarsName.text = result.carName
                    tvCarsCostDay.text = result.costDay
                    tvCarsCostMonth.text = result.costMonth

                    ivCarsImages.setImageResource(result.carImage!!)

                    root.setOnClickListener{
                        onClick?.invoke(result)
                    }
                }
            }
    }

    inner class SubMainFooterVH(private val binding: ItemSubMainFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

}