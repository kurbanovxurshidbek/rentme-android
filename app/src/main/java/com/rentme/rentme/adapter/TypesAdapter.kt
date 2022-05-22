package com.rentme.rentme.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rentme.rentme.databinding.ItemTypesLayoutBinding
import com.rentme.rentme.model.Types

class TypesAdapter : RecyclerView.Adapter<TypesAdapter.TypesViewHolder>() {
    private val dif = AsyncListDiffer(this, ITEM_DIF)
    var onClick: ((Types) -> Unit)? = null

    inner class TypesViewHolder(private val binding: ItemTypesLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val types = dif.currentList[adapterPosition]
            binding.apply {
                tvCarsName.text = types.carName
                tvCarsCount.text = types.carsNumber
                ivCarImage.setImageResource(types.carImage)

                root.setOnClickListener {
                    onClick?.invoke(types)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypesViewHolder {
        return TypesViewHolder(
            ItemTypesLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TypesViewHolder, position: Int) = holder.bind()

    override fun getItemCount(): Int = dif.currentList.size

    fun submitData(list: List<Types>){
        dif.submitList(list)
    }

    companion object {
        private val ITEM_DIF = object : DiffUtil.ItemCallback<Types>() {

            override fun areItemsTheSame(oldItem: Types, newItem: Types): Boolean =
                oldItem.carName == newItem.carName && oldItem.carsNumber == newItem.carsNumber

            override fun areContentsTheSame(oldItem: Types, newItem: Types): Boolean =
                oldItem == newItem
        }
    }
}