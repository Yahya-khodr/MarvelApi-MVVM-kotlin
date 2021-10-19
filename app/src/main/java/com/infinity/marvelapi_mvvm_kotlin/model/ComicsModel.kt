package com.infinity.marvelapi_mvvm_kotlin.model

import com.google.gson.annotations.SerializedName
import com.infinity.marvelapi_mvvm_kotlin.model.ComicItemModel
import java.io.Serializable


data class ComicsModel(
    @SerializedName("available") val available: Int,
    @SerializedName("collectionURI") val collectionURI: String,
    @SerializedName("items") val comicITem: List<ComicItemModel>,
    @SerializedName("returned") val returned: Int
) : Serializable