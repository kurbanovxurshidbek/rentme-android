package com.rentme.rentme.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rentme.rentme.data.local.entity.ModelsListEntity
import com.rentme.rentme.databinding.ItemSearchModelNameBinding

class SearchAdapter : ListAdapter<ModelsListEntity, SearchAdapter.VH>(ITEM_DIF), Filterable {
    var onClick: ((ModelsListEntity) -> Unit)? = null
    var carNames: List<ModelsListEntity> = ArrayList()

    inner class VH(private val binding: ItemSearchModelNameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ModelsListEntity) {
            binding.apply {
                tvHistoryLocation.text = item.modelName
                root.setOnClickListener {
                    onClick?.invoke(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemSearchModelNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    fun setData(list: List<ModelsListEntity>) {
        this.carNames = list
        submitList(list)
    }

    companion object {
        private val ITEM_DIF = object : DiffUtil.ItemCallback<ModelsListEntity>() {
            override fun areItemsTheSame(
                oldItem: ModelsListEntity,
                newItem: ModelsListEntity
            ): Boolean =
                oldItem.modelName == newItem.modelName

            override fun areContentsTheSame(
                oldItem: ModelsListEntity,
                newItem: ModelsListEntity
            ): Boolean =
                oldItem.modelName == newItem.modelName
        }
    }

    override fun getFilter(): Filter {
        return customFilter
    }

    private val customFilter = object : Filter() {
        override fun performFiltering(p0: CharSequence?): FilterResults {
            val filterableList = mutableListOf<ModelsListEntity>()
            if (p0 == null || p0.isEmpty()) {
                filterableList.addAll(carNames)
            } else {
                for (item in carNames) {
                    if (item.modelName.lowercase().contains(p0.toString().lowercase())) {
                        filterableList.add(item)
                    }
                }
            }

            val results = FilterResults()
            results.values = filterableList
            return results
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            submitList(p1?.values as MutableList<ModelsListEntity>)
        }

    }
}