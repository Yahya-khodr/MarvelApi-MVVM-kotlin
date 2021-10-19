package com.infinity.marvelapi_mvvm_kotlin.utils


import com.infinity.marvelapi_mvvm_kotlin.network.EndPoints
import java.math.BigInteger
import java.security.MessageDigest

object NetworkUtils {

    fun getTimeStamp(): String {
        return System.currentTimeMillis().toString()
    }

    fun getMD5Hash(timeStamp: String): String {
        val hashString = timeStamp + EndPoints.PRIVATE_KEY + EndPoints.PUBLIC_KEY
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(hashString.toByteArray())).toString(16).padStart(32, '0')
    }
}