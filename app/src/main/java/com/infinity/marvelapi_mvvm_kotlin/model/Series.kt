package com.infinity.marvelapi_mvvm_kotlin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Series(
    @SerializedName("available")val available: Int,
    @SerializedName("collectionURI")val collectionURI: String,
    @SerializedName("items") val seriesItem: List<SeriesItemModel>,
    @SerializedName("returned")val returned: Int
) : Serializable