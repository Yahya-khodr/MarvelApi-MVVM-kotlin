package com.infinity.marvelapi_mvvm_kotlin.base

import com.google.gson.annotations.SerializedName

class BaseResponse<T> {

    @SerializedName("code") var code:Int?=null

    @SerializedName("status") var status:String?=null

    @SerializedName("copyright") var copyright:String?=null

    @SerializedName("attributionText") var attributionText:String?=null

    @SerializedName("attributionHTML") var attributionHTML:String?=null

    @SerializedName("etag") var eTag:String?=null

    @SerializedName("data")
    var data: T? = null

}