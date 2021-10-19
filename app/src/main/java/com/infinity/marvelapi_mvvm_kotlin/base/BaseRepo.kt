package com.infinity.marvelapi_mvvm_kotlin.base


import com.infinity.marvelapi_mvvm_kotlin.network.NetworkManager
import javax.inject.Inject

open class BaseRepo {

    @Inject
    lateinit var networkManager: NetworkManager
}