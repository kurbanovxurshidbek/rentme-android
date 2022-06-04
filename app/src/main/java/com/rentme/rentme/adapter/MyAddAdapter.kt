package com.rentme.rentme.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rentme.rentme.databinding.ItemMyUploadViewBinding
import com.rentme.rentme.model.Result


class MyAddAdapter:RecyclerView.Adapter<MyAddAdapter.MyAddViewHolder>() {
    private val dif = AsyncListDiffer(this,ITEM_DIF)
    var onClick:((Result) -> Unit)? = null

    inner class MyAddViewHolder(private val binding:ItemMyUploadViewBinding):
        RecyclerView.ViewHolder(binding.root){
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyAddViewHolder{
        return MyAddViewHolder(
            ItemMyUploadViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: MyAddViewHolder, position: Int) = holder.bind()

    override fun getItemCount(): Int = dif.currentList.size

    fun submitData(list: List<Result>){
        dif.submitList(list)
    }

    companion object {
        private val ITEM_DIF = object : DiffUtil.ItemCallback<Result>() {

            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
                oldItem.carName == newItem.carName && oldItem.carImage == newItem.carImage

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
                oldItem == newItem
        }
    }

}