package com.rentme.rentme.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rentme.rentme.data.local.entity.History
import com.rentme.rentme.databinding.ItemSearchHistoryLocationBinding

class HistoryLocationAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val dif = AsyncListDiffer(this, ITEM_DIF)
    var onClick:((History) -> Unit)? = null

    companion object{
        private val ITEM_DIF = object : DiffUtil.ItemCallback<History>(){
            override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HistoryLocationVH(
            ItemSearchHistoryLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HistoryLocationVH){
            holder.bind()
        }
    }

    override fun getItemCount() = dif.currentList.size

    fun submitData(list: List<History>){
        dif.submitList(list)
    }

    inner class HistoryLocationVH(private val itemViewBinding: ItemSearchHistoryLocationBinding): RecyclerView.ViewHolder(itemViewBinding.root){
        fun bind(){
            val history = dif.currentList[adapterPosition]

            itemViewBinding.tvHistoryLocation.apply {
                text = history.name
                setOnClickListener {
                    onClick!!.invoke(history)
                }
            }
        }
    }
}