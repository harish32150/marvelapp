package com.harish.marvelapp.ui.main.fragment

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.harish.marvelapp.ui.main.fragment.characters.MarvelCharactersFragment
import com.harish.marvelapp.ui.main.fragment.comics.MarvelComicsFragment

class MarvelFragmentsAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
  override fun getItemCount() = 2

  override fun createFragment(position: Int) = when (position) {
    0 -> MarvelCharactersFragment()
    1 -> MarvelComicsFragment()
    else -> throw IllegalArgumentException("$position not implemented with fragments adapter")
  }

  fun getPageTitle(position: Int) = when (position) {
    0 -> "Characters"
    1 -> "Comics"
    else -> throw IllegalArgumentException("$position not implemented with fragments adapter titles")
  }
}