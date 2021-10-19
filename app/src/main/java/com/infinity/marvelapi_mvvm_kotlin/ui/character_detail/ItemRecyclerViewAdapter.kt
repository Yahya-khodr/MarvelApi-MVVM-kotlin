package com.infinity.marvelapi_mvvm_kotlin.ui.character_detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.infinity.marvelapi_mvvm_kotlin.R
import com.infinity.marvelapi_mvvm_kotlin.databinding.ItemRecyclerLayoutBinding
import com.infinity.marvelapi_mvvm_kotlin.model.ItemModel
import com.infinity.marvelapi_mvvm_kotlin.ui.character_detail.ItemRecyclerViewAdapter.*

class ItemRecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewHolder>() {

    var list = mutableListOf<ItemModel>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    inner class RecyclerViewHolder(private val binding: ItemRecyclerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: ItemModel) {
            binding.tvName.text = model.title
            Glide.with(binding.root)
                .load(model.thumbnail?.path + "." + model.thumbnail?.extension)
                .placeholder(R.drawable.image_placeholder)
                .into(binding.ivCharacter)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding: ItemRecyclerLayoutBinding = ItemRecyclerLayoutBinding.inflate(
            LayoutInflater.from(
                parent.context
            ),
            parent,
            false
        )
//
//        val params = binding.root.layoutParams
//        params.width = (parent.measuredWidth * 0.3).toInt()
//
//        binding.root.layoutParams = params

        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}