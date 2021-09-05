package com.harish.marvelapp.ui.main.fragment.characters

import com.harish.marvelapp.R
import com.harish.marvelapp.databinding.FragmentMarvelCharactersBinding
import com.harish.marvelapp.ui.main.MarvelViewModel
import com.harish.marvelapp.ui.main.fragment.BaseMarvelFragment

class MarvelCharactersFragment :
  BaseMarvelFragment<FragmentMarvelCharactersBinding, MarvelViewModel>() {
  override fun layoutId() = R.layout.fragment_marvel_characters

  override fun viewModelClass() = MarvelViewModel::class
}