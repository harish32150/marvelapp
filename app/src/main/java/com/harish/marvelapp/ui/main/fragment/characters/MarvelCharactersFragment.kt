package com.harish.marvelapp.ui.main.fragment.characters

import android.os.Bundle
import android.view.View
import com.harish.marvelapp.R
import com.harish.marvelapp.databinding.FragmentMarvelCharactersBinding
import com.harish.marvelapp.ui.main.MarvelViewModel
import com.harish.marvelapp.ui.main.fragment.BaseMarvelFragment

class MarvelCharactersFragment :
  BaseMarvelFragment<FragmentMarvelCharactersBinding, MarvelViewModel>() {
  override fun layoutId() = R.layout.fragment_marvel_characters

  override fun viewModelClass() = MarvelViewModel::class

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    viewModel.fetch()
  }
}