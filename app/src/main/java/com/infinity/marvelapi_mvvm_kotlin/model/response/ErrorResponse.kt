package com.infinity.marvelapi_mvvm_kotlin.model.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("message") override var message: String? = null,
    @SerializedName("code") var code: String?=null,
) : Exception()