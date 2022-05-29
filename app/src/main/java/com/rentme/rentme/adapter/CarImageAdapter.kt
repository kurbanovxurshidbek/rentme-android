package com.rentme.rentme.adapter

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rentme.rentme.databinding.ItemCarImageViewBinding

class CarImageAdapter(var items: ArrayList<Uri>) : RecyclerView.Adapter<CarImageAdapter.CarImageViewHolder>() {

    private var state: Boolean = false

    inner class CarImageViewHolder(private val binding: ItemCarImageViewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Uri?){
            if (items.isNotEmpty()){
                binding.ivCarPhoto.setImageURI(item)
                if (state){
                    binding.ivClear.visibility = View.VISIBLE
                    binding.llSaveStorage.visibility = View.GONE
                }else{
                    binding.ivClear.visibility = View.GONE
                    binding.llSaveStorage.visibility = View.VISIBLE
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun saveCarImageStorage(){
        state = true
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarImageViewHolder {
        return CarImageViewHolder(ItemCarImageViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CarImageViewHolder, position: Int) = if(items.isNotEmpty())holder.bind(items[position]) else holder.bind(null)

    override fun getItemCount(): Int = if (items.isEmpty()) 2 else items.size

}