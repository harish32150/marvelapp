package com.harish.marvelapp.ui.main

import android.util.Log
import com.harish.marvelapp.network.MarvelService
import com.harish.marvelapp.ui.BaseViewModel
import com.harish.marvelapp.utils.extensions.onBackground

class MarvelViewModel(private val marvelService: MarvelService) : BaseViewModel() {

  fun fetch() {
    marvelService.characters("hulk", 5, 0)
      .onBackground()
      .subscribe { _res, _err ->
        Log.d("MarvelViewModel", "response: $_res, \nerror: $_err")
      }.queue()
  }
}