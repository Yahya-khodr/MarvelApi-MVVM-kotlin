package com.infinity.marvelapi_mvvm_kotlin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class CharacterModel(
    @SerializedName("comics") val comics: ComicsModel,
    @SerializedName("description") val description: String,
    @SerializedName("events") val events: Events,
    @SerializedName("id") val id: Int,
    @SerializedName("modified") val modified: String,
    @SerializedName("name") val name: String,
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("series") val series: Series,
    @SerializedName("stories") val stories: Stories,
    @SerializedName("thumbnail") val thumbnail: Thumbnail,
    @SerializedName("urls") val urls: List<Url>
) : Serializable