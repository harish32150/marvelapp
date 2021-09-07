package com.harish.marvelapp.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.harish.marvelapp.data.MarvelCharacter
import com.harish.marvelapp.network.MarvelService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class MarvelCharactersPagingSource(
  private val marvelService: MarvelService,
  private val query: String
) : RxPagingSource<Int, MarvelCharacter>() {
  override fun getRefreshKey(state: PagingState<Int, MarvelCharacter>): Int? {
    return null
  }

  override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, MarvelCharacter>> {
    val page = params.key ?: 0
    return marvelService.characters(query, params.loadSize, page * params.loadSize)
      .subscribeOn(Schedulers.io())
      .map { it.data }
      .map { _res ->
        LoadResult.Page(
          data = _res.results,
          prevKey = if (page == 0) null else page - 1,
          nextKey = if (_res.limit + _res.offset >= _res.total) null else page + 1,
        ) as LoadResult<Int, MarvelCharacter>
      }.onErrorReturn { LoadResult.Error(it) }
  }
}