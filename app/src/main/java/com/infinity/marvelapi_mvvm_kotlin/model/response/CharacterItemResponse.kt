package com.infinity.marvelapi_mvvm_kotlin.model.response

import com.infinity.marvelapi_mvvm_kotlin.model.ItemModel
import com.google.gson.annotations.SerializedName

data class CharacterItemResponse(
    @SerializedName("results") val result: List<ItemModel>,
)