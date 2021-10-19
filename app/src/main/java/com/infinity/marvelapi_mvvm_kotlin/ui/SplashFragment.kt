package com.infinity.marvelapi_mvvm_kotlin.ui

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.infinity.marvelapi_mvvm_kotlin.R
import com.infinity.marvelapi_mvvm_kotlin.base.AnyViewModel
import com.infinity.marvelapi_mvvm_kotlin.base.BaseFragment
import com.infinity.marvelapi_mvvm_kotlin.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.glide.transformations.BlurTransformation

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, AnyViewModel>() {

    override fun initBinding(): FragmentSplashBinding =
        FragmentSplashBinding.inflate(layoutInflater)

    override fun initViewModel(): AnyViewModel =
        ViewModelProvider(this)[AnyViewModel::class.java]

    override fun onFragmentCreated() {

        Glide.with(this).load(R.drawable.image_background)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 2)))
            .into(binding.ivBackground)

         Handler(Looper.getMainLooper()).postDelayed(this::navigate, 2000)
    }

    private fun navigate() {
        findNavController().navigate(
            SplashFragmentDirections.actionSplashFragmentToCharactersFragment())
    }
}