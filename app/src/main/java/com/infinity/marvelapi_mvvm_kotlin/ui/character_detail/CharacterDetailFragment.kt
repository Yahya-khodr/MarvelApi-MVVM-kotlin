package com.infinity.marvelapi_mvvm_kotlin.ui.character_detail

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.infinity.marvelapi_mvvm_kotlin.R
import com.infinity.marvelapi_mvvm_kotlin.base.BaseFragment
import com.infinity.marvelapi_mvvm_kotlin.databinding.FragmentCharacterDetailBinding
import com.infinity.marvelapi_mvvm_kotlin.model.CharacterModel
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.glide.transformations.BlurTransformation


@AndroidEntryPoint
class CharacterDetailFragment :
    BaseFragment<FragmentCharacterDetailBinding, CharacterDetailViewModel>() {

    private val args: CharacterDetailFragmentArgs by navArgs()
    private lateinit var eventsAdapter: ItemRecyclerViewAdapter
    private lateinit var storiesAdapter: ItemRecyclerViewAdapter
    private lateinit var seriesAdapter: ItemRecyclerViewAdapter
    private lateinit var comicsAdapter: ItemRecyclerViewAdapter


    override fun initBinding(): FragmentCharacterDetailBinding =
        FragmentCharacterDetailBinding.inflate(layoutInflater)

    override fun initViewModel(): CharacterDetailViewModel =
        ViewModelProvider(this)[CharacterDetailViewModel::class.java]

    private lateinit var character: CharacterModel
    override fun onFragmentCreated() {
        character = args.character
        initAdapter()
        initView()
        observeViewModel()

        binding.ibBack.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun initAdapter() {
        eventsAdapter = ItemRecyclerViewAdapter()
        binding.layoutEvents.rvItem.adapter = eventsAdapter
        binding.layoutEvents.tvHeader.text = resources.getString(R.string.events)

        storiesAdapter = ItemRecyclerViewAdapter()
        binding.layoutStories.rvItem.adapter = storiesAdapter
        binding.layoutStories.tvHeader.text = resources.getString(R.string.stories)

        comicsAdapter = ItemRecyclerViewAdapter()
        binding.layoutComics.rvItem.adapter = comicsAdapter
        binding.layoutComics.tvHeader.text = resources.getString(R.string.comics)

        seriesAdapter = ItemRecyclerViewAdapter()
        binding.layoutSeries.rvItem.adapter = seriesAdapter
        binding.layoutSeries.tvHeader.text = resources.getString(R.string.series)

    }


    private fun initView() {
        binding.tvDescription.text=character.description
        binding.tvName.text=character.name
        Glide.with(binding.root)
            .load(character.thumbnail.path + "." + character.thumbnail.extension)
            .placeholder(R.drawable.image_placeholder)
            .into(binding.ivIcon)

        Glide.with(this).load(character.thumbnail.path + "." + character.thumbnail.extension)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 2)))
            .into(binding.ivBackground)

        if(character.comics.comicITem.isNullOrEmpty())
            binding.layoutComics.tvHeader.visibility = View.GONE
        else
            viewModel.getComic(character.id)

        if(character.stories.storiesItem.isNullOrEmpty())
            binding.layoutStories.tvHeader.visibility = View.GONE
        else
            viewModel.getStory(character.id)

        if(character.events.items.isNullOrEmpty())
            binding.layoutEvents.tvHeader.visibility = View.GONE
        else
            viewModel.getEvent(character.id)

        if(character.series.seriesItem.isNullOrEmpty())
            binding.layoutSeries.tvHeader.visibility = View.GONE
        else
            viewModel.getSeries(character.id)
    }

    private fun observeViewModel() {
        viewModel.eventLiveData.observe(this, {
            it.data?.result?.apply {
                eventsAdapter.list=this.toMutableList()
            }
        })
        viewModel.comicLiveData.observe(this, {
            it.data?.result?.apply {
                comicsAdapter.list=this.toMutableList()
            }
        })
        viewModel.storyLiveData.observe(this, {
            it.data?.result?.apply {
                storiesAdapter.list=this.toMutableList()
            }
        })
        viewModel.seriesLiveData.observe(this, {
            it.data?.result?.apply {
                seriesAdapter.list=this.toMutableList()
            }
        })
    }


}