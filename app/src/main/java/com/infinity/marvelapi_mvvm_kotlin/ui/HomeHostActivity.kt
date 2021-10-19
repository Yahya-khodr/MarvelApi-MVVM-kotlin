package com.infinity.marvelapi_mvvm_kotlin.ui

import androidx.lifecycle.ViewModelProvider
import com.infinity.marvelapi_mvvm_kotlin.base.AnyViewModel
import com.infinity.marvelapi_mvvm_kotlin.base.BaseActivity
import com.infinity.marvelapi_mvvm_kotlin.databinding.ActivityHomeHostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeHostActivity : BaseActivity<ActivityHomeHostBinding, AnyViewModel>() {

    override fun initBinding(): ActivityHomeHostBinding =
        ActivityHomeHostBinding.inflate(layoutInflater)

    override fun initViewModel(): AnyViewModel =
        ViewModelProvider(this)[AnyViewModel::class.java]

    override fun onActivityCreated() {
    }
}