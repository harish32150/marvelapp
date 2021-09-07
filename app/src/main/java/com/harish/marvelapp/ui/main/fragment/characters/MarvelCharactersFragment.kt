package com.harish.marvelapp.ui.main.fragment.characters

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.harish.marvelapp.R
import com.harish.marvelapp.databinding.FragmentMarvelCharactersBinding
import com.harish.marvelapp.ui.main.MarvelActivity
import com.harish.marvelapp.ui.main.MarvelViewModel
import com.harish.marvelapp.ui.main.fragment.BaseMarvelFragment
import com.harish.marvelapp.ui.main.fragment.MarvelFooterAdapter
import com.harish.marvelapp.utils.extensions.actionDone

class MarvelCharactersFragment :
  BaseMarvelFragment<FragmentMarvelCharactersBinding, MarvelViewModel>() {
  override fun layoutId() = R.layout.fragment_marvel_characters

  override fun viewModelClass() = MarvelViewModel::class

  private val charactersAdapter by lazy { MarvelCharactersRVAdapter() }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.rv.apply {
      layoutManager = LinearLayoutManager(this@MarvelCharactersFragment.requireContext())
      adapter = charactersAdapter.withLoadStateFooter(MarvelFooterAdapter {
        charactersAdapter.retry()
      })
    }

    viewModel.characters.observe(activity as MarvelActivity, {
      charactersAdapter.submitData(lifecycle, it)
    })

    binding.ibtnSearch.setOnClickListener { searchImages(binding.editSearch.text.toString()) }
    binding.editSearch.actionDone {
      searchImages(it)
    }
  }

  /* search images */
  private fun searchImages(query: String) {
    if (query.isNullOrEmpty()) {
      uiUtils.toast("Enter a query")
      return
    }
    uiUtils.apply {
      toggleKeyboard(hide = true)
      toast("Searching images for $query")
    }
    viewModel.searchCharacters(query)
  }
}