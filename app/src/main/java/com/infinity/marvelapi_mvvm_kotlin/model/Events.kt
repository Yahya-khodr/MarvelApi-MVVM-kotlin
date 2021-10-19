package com.infinity.marvelapi_mvvm_kotlin.model

import com.google.gson.annotations.SerializedName
import com.infinity.marvelapi_mvvm_kotlin.model.EventITemModel
import java.io.Serializable


data class Events(
    @SerializedName("available")val available: Int,
    @SerializedName("collectionURI")val collectionURI: String,
    @SerializedName("items")val items: List<EventITemModel>,
    @SerializedName("returned")val returned: Int
) : Serializable