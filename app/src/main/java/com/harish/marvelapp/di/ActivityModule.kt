package com.harish.marvelapp.di

import androidx.appcompat.app.AppCompatActivity
import com.harish.marvelapp.ui.main.MarvelActivity
import com.harish.marvelapp.ui.main.fragment.characters.MarvelCharactersFragment
import com.harish.marvelapp.ui.main.fragment.comics.MarvelComicsFragment
import com.harish.marvelapp.utils.UIUtils
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

val activityModule = module {
  factory { (activity: AppCompatActivity) ->
    UIUtils(activity)
  }

  /**
   * Activity specific scoped fragments
   */
  scope<MarvelActivity> {
    fragment { MarvelCharactersFragment() }
    fragment { MarvelComicsFragment() }
  }
}