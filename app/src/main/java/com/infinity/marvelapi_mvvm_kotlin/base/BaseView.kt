package com.infinity.marvelapi_mvvm_kotlin.base


import com.infinity.marvelapi_mvvm_kotlin.model.response.ErrorResponse

interface BaseView {
    fun onError(error: ErrorResponse)

    fun showLoading()

    fun hideLoading()

    fun showSuccessMsg(msg: String)

    fun showErrorMsg(msg: String)
}