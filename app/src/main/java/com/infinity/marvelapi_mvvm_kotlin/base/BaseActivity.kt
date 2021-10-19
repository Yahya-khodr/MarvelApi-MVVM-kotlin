package com.infinity.marvelapi_mvvm_kotlin.base

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.infinity.marvelapi_mvvm_kotlin.model.response.ErrorResponse

abstract class BaseActivity <V : ViewBinding, VM : BaseViewModel> : AppCompatActivity(), BaseView {


    open lateinit var binding: V
    open lateinit var viewModel: VM

    abstract fun initBinding(): V

    abstract fun initViewModel(): VM

    abstract fun onActivityCreated()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = initViewModel()
        binding = initBinding()
        setContentView(binding.root)

        viewModel.errorLiveData.observe(this, this::onError)


        onActivityCreated()
    }

    override fun onError(error: ErrorResponse) {
        hideLoading()
        showErrorMsg(error.toString())
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showSuccessMsg(msg: String) {
//        Toasty.success(this, msg, Toast.LENGTH_LONG, false).show()
    }

    override fun showErrorMsg(msg: String) {
//        Toasty.error(this, msg, Toast.LENGTH_LONG, false).show()
    }

    fun returnError(error: String): Boolean {
        showErrorMsg(error)
        return false
    }

    fun showKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    fun hideKeyboard() {
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}