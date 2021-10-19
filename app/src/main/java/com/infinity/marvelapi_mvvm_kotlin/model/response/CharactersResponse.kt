package com.infinity.marvelapi_mvvm_kotlin.model.response


import com.infinity.marvelapi_mvvm_kotlin.model.CharacterModel
import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("count")val count: Int,
    @SerializedName("limit")val limit: Int,
    @SerializedName("offset")val offset: Int,
    @SerializedName("results")val characters: List<CharacterModel>,
    @SerializedName("total")val total: Int
)