package com.infinity.marvelapi_mvvm_kotlin.ui.characters

import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.infinity.marvelapi_mvvm_kotlin.utils.PaginationUtils
import com.google.android.material.textfield.TextInputEditText
import com.infinity.marvelapi_mvvm_kotlin.base.BaseFragment
import com.infinity.marvelapi_mvvm_kotlin.databinding.FragmentCharactersBinding
import com.infinity.marvelapi_mvvm_kotlin.model.CharacterModel
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.blurry.Blurry

@AndroidEntryPoint
class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharacterViewModel>() {

    private lateinit var characterAdapter: CharactersAdapter
    private lateinit var searchAdapter: SearchCharacterAdapter
    private var isLoadMore = false

    private var searchEnabled = false

    override fun initBinding(): FragmentCharactersBinding =
        FragmentCharactersBinding.inflate(layoutInflater)


    override fun initViewModel(): CharacterViewModel =
        ViewModelProvider(this)[CharacterViewModel::class.java]

    override fun onFragmentCreated() {
        initCharacterAdapter()
        getCharacters()
        handlePagination()
        getSearch()
        initSearchAdapter()

        binding.ibSearch.setOnClickListener {
            searchEnabled = !searchEnabled
            if (searchEnabled) {
                binding.layoutSearch.root.visibility = View.VISIBLE
                binding.btnCancel.visibility = View.VISIBLE
                binding.ibSearch.visibility = View.GONE
                binding.rvSearch.visibility=View.VISIBLE
                binding.mcvAppBar.visibility=View.GONE
                Blurry.with(requireContext())
                    .radius(25)
                    .sampling(10)
                    .async()
                    .onto(binding.clHome)

            }
        }
        binding.btnCancel.setOnClickListener {
            searchEnabled = !searchEnabled
            hideKeyboard()
            if (!searchEnabled) {
                binding.layoutSearch.root.visibility = View.GONE
                binding.btnCancel.visibility = View.GONE
                binding.ibSearch.visibility = View.VISIBLE
                binding.rvSearch.visibility=View.GONE
                binding.mcvAppBar.visibility=View.VISIBLE
                binding.layoutSearch.etSearch.text?.clear()
                searchAdapter.list.clear()

                Blurry.delete(binding.clHome)
            }
        }
        binding.layoutSearch.etSearch.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query=(v as TextInputEditText).text.toString().trim()
                handleSearch(query)
                hideKeyboard()
                searchAdapter.searchString=query
                return@setOnEditorActionListener true
            }
            false
        }
    }

    private fun getSearch() {
        viewModel.searchLiveData.observe(this,{
            binding.rvSearch.visibility=View.VISIBLE
            searchAdapter.list = it.data?.characters as MutableList<CharacterModel>
        })
    }

    private fun handleSearch(query: String) {
        viewModel.searchCharacter(query)
    }

    private fun initCharacterAdapter() {
        characterAdapter = CharactersAdapter {
            findNavController().navigate(CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(it))
        }
        binding.rvCharacters.adapter = characterAdapter
    }
    private fun initSearchAdapter() {
        searchAdapter = SearchCharacterAdapter()
        binding.rvSearch.adapter = searchAdapter
    }

    private fun getCharacters() {
        viewModel.characterLiveData.observe(this, {
            hideLoading()

            characterAdapter.list = it.data?.characters as MutableList<CharacterModel>
            if (isLoadMore) isLoadMore = false

        })
    }

    private fun handlePagination() {
        PaginationUtils.addLoadMoreListener(binding.rvCharacters) {
            if (!isLoadMore && viewModel.getCurrentPage() < viewModel.getTotalPage()) {
                isLoadMore = true
                viewModel.nextPage()
                showLoading()
                viewModel.getCharacters()
            }
        }
    }

    override fun showLoading() {
        if (isLoadMore) binding.pbMore.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.pbMore.visibility = View.GONE
        binding.pbLoader.visibility = View.GONE
        binding.rvCharacters.visibility = View.VISIBLE
    }
}