package com.infinity.marvelapi_mvvm_kotlin.ui.characters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.infinity.marvelapi_mvvm_kotlin.R
import com.infinity.marvelapi_mvvm_kotlin.databinding.ItemCharacterLayoutBinding
import com.infinity.marvelapi_mvvm_kotlin.ui.characters.CharactersAdapter.CharacterViewHolder
import com.infinity.marvelapi_mvvm_kotlin.model.CharacterModel

class CharactersAdapter(private var onClickListener: (model: CharacterModel) -> Unit) : RecyclerView.Adapter<CharacterViewHolder>() {

    var list = mutableListOf<CharacterModel>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field.addAll(value)
            notifyDataSetChanged()
        }

    inner class CharacterViewHolder(private val binding: ItemCharacterLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: CharacterModel) {
            binding.tvName.text=model.name
            Glide.with(binding.root)
                .load(model.thumbnail.path+"."+model.thumbnail.extension).placeholder(R.drawable.image_placeholder)
                .into(binding.ivCharacter)
            binding.root.setOnClickListener {
                onClickListener.invoke(model)
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}