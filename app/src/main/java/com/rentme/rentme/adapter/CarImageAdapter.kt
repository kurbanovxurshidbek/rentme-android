package com.rentme.rentme.adapter

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rentme.rentme.databinding.ItemCarImageViewBinding

class CarImageAdapter() : RecyclerView.Adapter<CarImageAdapter.CarImageViewHolder>() {

    var items: ArrayList<Uri?> = ArrayList()
    var state: Boolean = false
    var clickClear: ((Int) -> Unit)? = null
    var clickAddCarImage: (() -> Unit)? = null

    inner class CarImageViewHolder(private val binding: ItemCarImageViewBinding) : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("NotifyDataSetChanged")
        fun bind(item: Uri?, position: Int){
            if(position == 0){
                binding.llCarAdd.visibility = View.VISIBLE
                binding.flCarImage.visibility = View.GONE
            }else{
                binding.llCarAdd.visibility = View.GONE
                binding.flCarImage.visibility = View.VISIBLE
            }

            binding.ivAddPhoto.setOnClickListener {
                clickAddCarImage?.invoke()
            }

            if (items.isNotEmpty()){
                binding.ivCarPhoto.setImageURI(item)
                if (state){
                    binding.ivClear.visibility = View.VISIBLE
                    binding.llSaveStorage.visibility = View.GONE
                }else{
                    binding.ivClear.visibility = View.GONE
                    binding.llSaveStorage.visibility = View.VISIBLE
                }
                binding.ivClear.setOnClickListener {
                    items.remove(item)
                    clickClear?.invoke(position)
                    notifyDataSetChanged()
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun saveCarImageStorage(carImages: ArrayList<Uri>){
        state = true
        items.clear()
        items.addAll(carImages)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarImageViewHolder {
        return CarImageViewHolder(ItemCarImageViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CarImageViewHolder, position: Int) = if(items.size > 1)holder.bind(items[position], position) else holder.bind(null, position)

    override fun getItemCount(): Int = if (items.size > 1) items.size else 3

}