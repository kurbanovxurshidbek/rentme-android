package com.rentme.rentme.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rentme.rentme.databinding.ItemResultLayoutBinding
import com.rentme.rentme.model.filtermodel.Advertisement

class ResultAdapter:RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {
    private val dif = AsyncListDiffer(this,ITEM_DIF)
    var onClick:((Advertisement) -> Unit)? = null

    inner class ResultViewHolder(private val binding:ItemResultLayoutBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(){
                val result = dif.currentList[adapterPosition]
                binding.apply {
                    tvCarsName.text = result.transport.model!!.name
                    tvCarsCostDay.text = result.prices[0].quantity.toString()
                    tvCarsCostMonth.text = if (result.prices.size > 1) result.prices[1].quantity.toString() else ""

                    Glide.with(binding.root)
                        .load(result.transport.pictures?.get(0)!!.path)
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

    fun submitData(list: List<Advertisement>){
        dif.submitList(list)
    }

    companion object {
        private val ITEM_DIF = object : DiffUtil.ItemCallback<Advertisement>() {

            override fun areItemsTheSame(oldItem: Advertisement, newItem: Advertisement): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Advertisement, newItem: Advertisement): Boolean =
                oldItem == newItem
        }
    }

}