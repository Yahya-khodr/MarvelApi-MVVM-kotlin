package com.infinity.marvelapi_mvvm_kotlin.network


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.infinity.marvelapi_mvvm_kotlin.base.BaseResponse
import com.infinity.marvelapi_mvvm_kotlin.model.response.ErrorResponse
import com.infinity.marvelapi_mvvm_kotlin.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class NetworkManager @Inject constructor() {

    @Inject
    lateinit var retrofit: Retrofit

    private val headers: MutableMap<String, String> =
        object : HashMap<String, String>() {
            init {
                put("Content-Type", "application/json")
            }
        }


    private fun updateDefaultParam():MutableMap<String,Any>{
        val timeStamp=NetworkUtils.getTimeStamp()
        val hash= NetworkUtils.getMD5Hash(timeStamp)
        return hashMapOf(
            "ts" to timeStamp,
            "hash" to hash,
            "apikey" to EndPoints.PUBLIC_KEY
        )
    }

    suspend fun <T> getRequest(
        api: String,
        param: MutableMap<String, Any> = HashMap(),
        parseClass: Class<T>,
    ): BaseResponse<T> {
        return withContext(Dispatchers.IO) {

            param.putAll(updateDefaultParam())

            parseResponse(
                retrofit.create(
                    APIService::class.java
                ).getRequest(api, headers, param), parseClass
            )
        }
    }


    private fun <T> parseResponse(
        response: Response<JsonElement>,
        parseClass: Class<T>,
    ): BaseResponse<T> {
        return try {
            val gson = GsonBuilder().serializeNulls().create()
            if (!response.isSuccessful) {
                var errorResponse = ErrorResponse()
                response.errorBody()?.let {
                    try {
                        val obj = JSONObject(response.errorBody()!!.string())
                        try {
                            val temp = Gson().fromJson(obj.toString(), ErrorResponse::class.java)
                            temp?.let { errorResponse = it }
                        } catch (e: IllegalStateException) {
                        }
                    } catch (ex: JSONException) {
                    }
                }
                throw errorResponse
            } else {
                BaseResponse<T>().apply {
                    code = response.body()!!.asJsonObject["code"].asInt
                    status = response.body()!!.asJsonObject["status"].asString
                    copyright = response.body()!!.asJsonObject["copyright"].asString
                    attributionText = response.body()!!.asJsonObject["attributionText"].asString
                    attributionHTML = response.body()!!.asJsonObject["attributionHTML"].asString
                    eTag = response.body()!!.asJsonObject["etag"].asString
                    data = gson.fromJson(response.body()!!.asJsonObject["data"], parseClass)
                }
            }
        } catch (e: Exception) {
            throw e
        }
    }
}