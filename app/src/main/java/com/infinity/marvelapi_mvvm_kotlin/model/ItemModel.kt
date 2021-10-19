package com.infinity.marvelapi_mvvm_kotlin.model

import com.google.gson.annotations.SerializedName

data class ItemModel (
    @SerializedName("thumbnail") val thumbnail: Thumbnail?=null,
    @SerializedName("title") val title: String,
    )