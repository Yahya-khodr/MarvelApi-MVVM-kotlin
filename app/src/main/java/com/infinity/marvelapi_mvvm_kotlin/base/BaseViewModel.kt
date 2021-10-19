package com.infinity.marvelapi_mvvm_kotlin.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.infinity.marvelapi_mvvm_kotlin.model.response.ErrorResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException


open class BaseViewModel : ViewModel() {


    inline fun CoroutineScope.launchCatching(
        noinline block: suspend CoroutineScope.() -> Unit,
        crossinline onError: (Throwable) -> Unit,
    ) {
        launch(
            CoroutineExceptionHandler { _, throwable -> onError(throwable) },
            block = block
        )
    }

    val errorLiveData: MutableLiveData<ErrorResponse> by lazy {
        MutableLiveData<ErrorResponse>()
    }

    fun handleError(t: Throwable) {
        val error: ErrorResponse = when (t) {
            is ErrorResponse -> {
                t
            }
            is HttpException -> {
                ErrorResponse("Network Error")
            }
            is UnknownHostException -> {
                ErrorResponse("No Internet")
            }
            else -> {
                ErrorResponse("Something Went Wrong")
            }
        }
        errorLiveData.postValue(error)
    }
}
