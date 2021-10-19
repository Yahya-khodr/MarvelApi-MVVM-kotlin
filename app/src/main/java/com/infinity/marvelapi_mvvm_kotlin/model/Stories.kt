package com.infinity.marvelapi_mvvm_kotlin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Stories(
    @SerializedName("available") val available: Int,
    @SerializedName("collectionURI") val collectionURI: String,
    @SerializedName("items") val storiesItem: List<StoriesItemModel>,
    @SerializedName("returned") val returned: Int
) : Serializable