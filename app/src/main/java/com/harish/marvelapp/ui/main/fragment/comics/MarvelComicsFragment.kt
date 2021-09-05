package com.harish.marvelapp.ui.main.fragment.comics

import com.harish.marvelapp.R
import com.harish.marvelapp.databinding.FragmentMarvelComicsBinding
import com.harish.marvelapp.ui.main.MarvelViewModel
import com.harish.marvelapp.ui.main.fragment.BaseMarvelFragment

class MarvelComicsFragment : BaseMarvelFragment<FragmentMarvelComicsBinding, MarvelViewModel>() {
  override fun layoutId() = R.layout.fragment_marvel_characters

  override fun viewModelClass() = MarvelViewModel::class
}