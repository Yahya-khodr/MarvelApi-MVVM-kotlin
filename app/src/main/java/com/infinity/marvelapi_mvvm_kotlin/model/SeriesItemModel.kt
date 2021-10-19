package com.infinity.marvelapi_mvvm_kotlin.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SeriesItemModel(
    @SerializedName("name") val name: String,
    @SerializedName("resourceURI") val resourceURI: String
) : Serializable