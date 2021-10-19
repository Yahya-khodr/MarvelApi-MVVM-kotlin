package com.infinity.marvelapi_mvvm_kotlin.ui.characters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.infinity.marvelapi_mvvm_kotlin.ui.characters.SearchCharacterAdapter.SearchViewHolder
import com.infinity.marvelapi_mvvm_kotlin.utils.StringUtils.highLightText
import com.infinity.marvelapi_mvvm_kotlin.R
import com.infinity.marvelapi_mvvm_kotlin.databinding.ItemCharacterSearchLayoutBinding
import com.infinity.marvelapi_mvvm_kotlin.model.CharacterModel


class SearchCharacterAdapter() : RecyclerView.Adapter<SearchViewHolder>() {

    var searchString = ""
    var list = mutableListOf<CharacterModel>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    inner class SearchViewHolder(private val binding: ItemCharacterSearchLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: CharacterModel) {

            binding.tvName.text = model.name.lowercase()
            binding.tvName.highLightText(searchString)
            Glide.with(binding.root)
                .load(model.thumbnail.path + "." + model.thumbnail.extension)
                .placeholder(R.drawable.image_placeholder)
                .into(binding.ivCharacter)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SearchViewHolder {
        return SearchViewHolder(
            ItemCharacterSearchLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}