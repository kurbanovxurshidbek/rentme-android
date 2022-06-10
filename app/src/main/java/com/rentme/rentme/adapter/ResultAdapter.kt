package com.rentme.rentme.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rentme.rentme.databinding.ItemResultLayoutBinding
import com.rentme.rentme.model.Result
import com.rentme.rentme.model.filtermodel.Filter

class ResultAdapter:RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {
    private val dif = AsyncListDiffer(this,ITEM_DIF)
    var onClick:((Filter) -> Unit)? = null

    inner class ResultViewHolder(private val binding:ItemResultLayoutBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(){
                val result = dif.currentList[adapterPosition]
                binding.apply {
                    tvCarsName.text = result.transport.model.name
                    tvCarsCostDay.text = result.prices[0].quantity.toString()
                    tvCarsCostMonth.text = if (result.prices.size > 1) result.prices[1].quantity.toString() else ""

                    Glide.with(binding.root)
                        .load(result.transport.pictures[0].path)
                        .into(ivCarsImages)

                    root.setOnClickListener{
                        onClick?.invoke(result)
                    }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ResultViewHolder{
        return ResultViewHolder(
            ItemResultLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) = holder.bind()

    override fun getItemCount(): Int = dif.currentList.size

    fun submitData(list: List<Filter>){
        dif.submitList(list)
    }

    companion object {
        private val ITEM_DIF = object : DiffUtil.ItemCallback<Filter>() {

            override fun areItemsTheSame(oldItem: Filter, newItem: Filter): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Filter, newItem: Filter): Boolean =
                oldItem == newItem
        }
    }

}