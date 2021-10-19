package com.infinity.marvelapi_mvvm_kotlin.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.infinity.marvelapi_mvvm_kotlin.model.response.ErrorResponse

abstract class BaseFragment<V : ViewBinding, VM : BaseViewModel> : Fragment(), BaseView {


    private var _binding: V? = null
    val binding get() = _binding!!

    open lateinit var viewModel: VM


    abstract fun initBinding(): V

    abstract fun initViewModel(): VM

    abstract fun onFragmentCreated()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        super.onCreate(savedInstanceState)
        if (_binding == null) {
            viewModel = initViewModel()
            _binding = initBinding()
            viewModel.errorLiveData.observe(viewLifecycleOwner, this::onError)

            onFragmentCreated()
        }

        return binding.root
    }

    override fun onError(error: ErrorResponse) {
        hideLoading()
        showErrorMsg(error.toString())
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    fun hideKeyboard() {
        (requireActivity() as BaseActivity<*, *>).hideKeyboard()
    }

    override fun onDestroyView() {
        hideLoading()
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun returnError(error: String): Boolean {
        showErrorMsg(error)
        return false
    }

    override fun showSuccessMsg(msg: String) {
//        if (context != null)
//            Toasty.success(requireContext(), msg, Toast.LENGTH_LONG, false).show()
    }

    override fun showErrorMsg(msg: String) {
//        if (context != null)
//            Toasty.error(requireContext(), msg, Toast.LENGTH_LONG, false).show()
    }
}