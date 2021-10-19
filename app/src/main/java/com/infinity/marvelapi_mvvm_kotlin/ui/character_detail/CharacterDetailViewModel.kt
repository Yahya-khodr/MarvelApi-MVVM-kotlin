package com.infinity.marvelapi_mvvm_kotlin.ui.character_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.infinity.marvelapi_mvvm_kotlin.base.BaseResponse
import com.infinity.marvelapi_mvvm_kotlin.base.BaseViewModel
import com.infinity.marvelapi_mvvm_kotlin.model.response.CharacterItemResponse
import com.infinity.marvelapi_mvvm_kotlin.repo.CharacterRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val characterRepo: CharacterRepo) :
    BaseViewModel() {
    private val _storyMutableLiveData = MutableLiveData<BaseResponse<CharacterItemResponse>>()
    val storyLiveData: LiveData<BaseResponse<CharacterItemResponse>> = _storyMutableLiveData
    private val _eventMutableLiveData = MutableLiveData<BaseResponse<CharacterItemResponse>>()
    val eventLiveData: LiveData<BaseResponse<CharacterItemResponse>> = _eventMutableLiveData
    private val _seriesMutableLiveData = MutableLiveData<BaseResponse<CharacterItemResponse>>()
    val seriesLiveData: LiveData<BaseResponse<CharacterItemResponse>> = _seriesMutableLiveData
    private val _comicMutableLiveData = MutableLiveData<BaseResponse<CharacterItemResponse>>()
    val comicLiveData: LiveData<BaseResponse<CharacterItemResponse>> = _comicMutableLiveData


    fun getStory(id: Int) {
        viewModelScope.launchCatching(
            block = {
                val response = characterRepo.getStory(hashMapOf("characters" to id))

                _storyMutableLiveData.value = response
            }, onError = ::handleError
        )
    }

    fun getEvent(id: Int) {
        viewModelScope.launchCatching(
            block = {
                val response = characterRepo.getEvent(hashMapOf("characters" to id))

                _eventMutableLiveData.value = response
            }, onError = ::handleError
        )
    }

    fun getSeries(id: Int) {
        viewModelScope.launchCatching(
            block = {
                val response = characterRepo.getSeries(hashMapOf("characters" to id))

                _seriesMutableLiveData.value = response
            }, onError = ::handleError
        )
    }

    fun getComic(id: Int) {
        viewModelScope.launchCatching(
            block = {
                val response = characterRepo.getComic(hashMapOf("characters" to id))
                _comicMutableLiveData.value = response
            }, onError = ::handleError
        )
    }


}