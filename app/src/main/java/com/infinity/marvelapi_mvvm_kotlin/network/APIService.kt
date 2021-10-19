package com.infinity.marvelapi_mvvm_kotlin.network

import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface APIService {

    @GET
    @JvmSuppressWildcards
    suspend fun getRequest(
        @Url api: String,
        @HeaderMap headers: Map<String, Any>?,
        @QueryMap param: Map<String, Any>?
    ): Response<JsonElement>

}