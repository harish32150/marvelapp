package com.harish.marvelapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.harish.marvelapp.data.MarvelCharacter
import com.harish.marvelapp.data.MarvelComic
import com.harish.marvelapp.repository.MarvelRepository
import com.harish.marvelapp.ui.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class MarvelViewModel(private val marvelRepository: MarvelRepository) : BaseViewModel() {

  val characters = MutableLiveData<PagingData<MarvelCharacter>>()
  val comics = MutableLiveData<PagingData<MarvelComic>>()

  /* search characters */
  fun searchCharacters(query: String) {
    marvelRepository.fetchCharacters(query)
      .cachedIn(viewModelScope)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe {
        characters.postValue(it)
      }.queue()
  }

  /* fetch comics for date range */
  fun fetchComics(dateRange: String) {
    marvelRepository.fetchComics(dateRange)
      .cachedIn(viewModelScope)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe {
        comics.postValue(it)
      }.queue()
  }
}