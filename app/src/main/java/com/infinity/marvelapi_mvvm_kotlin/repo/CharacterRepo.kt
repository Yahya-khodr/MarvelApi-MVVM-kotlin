package com.infinity.marvelapi_mvvm_kotlin.repo


import com.infinity.marvelapi_mvvm_kotlin.base.BaseRepo
import com.infinity.marvelapi_mvvm_kotlin.model.response.CharacterItemResponse
import com.infinity.marvelapi_mvvm_kotlin.model.response.CharactersResponse
import com.infinity.marvelapi_mvvm_kotlin.network.EndPoints
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterRepo @Inject constructor() : BaseRepo() {

    suspend fun getCharacters(param: HashMap<String, Any>) = withContext(Dispatchers.IO) {
        networkManager.getRequest(
            api = EndPoints.CHARACTERS,
            param = param,
            parseClass = CharactersResponse::class.java,
        )
    }
    suspend fun getStory(param: HashMap<String, Any>) = withContext(Dispatchers.IO) {
        networkManager.getRequest(
            api = EndPoints.STORIES,
            param=param,
            parseClass = CharacterItemResponse::class.java,
        )
    }
    suspend fun getEvent(param: HashMap<String, Any>) = withContext(Dispatchers.IO) {
        networkManager.getRequest(
            api = EndPoints.EVENTS,
            param=param,
            parseClass = CharacterItemResponse::class.java,
        )
    }
    suspend fun getSeries(param: HashMap<String, Any>) = withContext(Dispatchers.IO) {
        networkManager.getRequest(
            api = EndPoints.SERIES,
            param=param,
            parseClass = CharacterItemResponse::class.java,
        )
    }
    suspend fun getComic(param: HashMap<String, Any>) = withContext(Dispatchers.IO) {
        networkManager.getRequest(
            api = EndPoints.COMICS,
            param=param,
            parseClass = CharacterItemResponse::class.java,
        )
    }
}