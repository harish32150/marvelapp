package com.harish.marvelapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.harish.marvelapp.data.MarvelCharacter
import com.harish.marvelapp.data.MarvelComic
import com.harish.marvelapp.network.MarvelService
import com.harish.marvelapp.paging.MarvelCharactersPagingSource
import com.harish.marvelapp.paging.MarvelComicPagingSource
import io.reactivex.rxjava3.core.Flowable

class MarvelRepository(private val marvelService: MarvelService) {

  /* fetch marvel characters as pager */
  fun fetchCharacters(query: String): Flowable<PagingData<MarvelCharacter>> =
    Pager(
      config = PagingConfig(pageSize = 2, enablePlaceholders = false),
      pagingSourceFactory = { MarvelCharactersPagingSource(marvelService, query) }
    ).flowable

  /* fetch marvel comics as pager */
  fun fetchComics(dateRange: String): Flowable<PagingData<MarvelComic>> =
    Pager(
      config = PagingConfig(pageSize = 5, enablePlaceholders = false),
      pagingSourceFactory = { MarvelComicPagingSource(marvelService, dateRange) }
    ).flowable
}