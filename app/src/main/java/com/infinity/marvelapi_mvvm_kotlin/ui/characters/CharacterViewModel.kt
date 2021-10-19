package com.infinity.marvelapi_mvvm_kotlin.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.infinity.marvelapi_mvvm_kotlin.base.BaseResponse
import com.infinity.marvelapi_mvvm_kotlin.base.BaseViewModel
import com.infinity.marvelapi_mvvm_kotlin.model.response.CharactersResponse
import com.infinity.marvelapi_mvvm_kotlin.repo.CharacterRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val characterRepo: CharacterRepo) :
    BaseViewModel() {

    private var _totalPages = -1
    fun getTotalPage(): Int = _totalPages

    private var _currentPage = 0
    fun getCurrentPage(): Int = _currentPage

    fun nextPage() {
        _currentPage++
    }

    private val _characterMutableLiveData = MutableLiveData<BaseResponse<CharactersResponse>>()
    val characterLiveData: LiveData<BaseResponse<CharactersResponse>> = _characterMutableLiveData
    private val _searchMutableLiveData = MutableLiveData<BaseResponse<CharactersResponse>>()
    val searchLiveData: LiveData<BaseResponse<CharactersResponse>> = _searchMutableLiveData

    init {
        getCharacters()
    }

    fun getCharacters() {
        viewModelScope.launchCatching(
            block = {
                val response = characterRepo.getCharacters(
                    hashMapOf(
                        "offset" to _currentPage
                    )
                )
                if (_totalPages == -1) {
                    _totalPages = response.data?.total ?: -1
                }
                _characterMutableLiveData.value = response
            }, onError = ::handleError
        )
    }

    fun searchCharacter(query:String) {
        viewModelScope.launchCatching(
            block = {
                val response = characterRepo.getCharacters(
                    hashMapOf(
                        "nameStartsWith" to query
                    )
                )
                _searchMutableLiveData.value = response
            }, onError = ::handleError
        )
    }
}