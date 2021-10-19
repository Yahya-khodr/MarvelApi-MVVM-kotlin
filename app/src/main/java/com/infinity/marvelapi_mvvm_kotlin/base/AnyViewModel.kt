package com.infinity.marvelapi_mvvm_kotlin.base

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnyViewModel @Inject constructor() : BaseViewModel()