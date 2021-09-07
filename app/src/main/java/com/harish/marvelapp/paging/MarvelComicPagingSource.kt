package com.harish.marvelapp.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.harish.marvelapp.data.MarvelComic
import com.harish.marvelapp.network.MarvelService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class MarvelComicPagingSource(
  private val marvelService: MarvelService,
  private val dateRange: String
): RxPagingSource<Int, MarvelComic>() {
  override fun getRefreshKey(state: PagingState<Int, MarvelComic>): Int? {
    return null
  }

  override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, MarvelComic>> {
    val page = params.key ?: 0
    return marvelService.comics(dateRange, params.loadSize, page * params.loadSize)
      .subscribeOn(Schedulers.io())
      .map { it.data }
      .map { _res ->
        LoadResult.Page(
          data = _res.results,
          prevKey = if (page == 0) null else page - 1,
          nextKey = if (_res.limit + _res.offset >= _res.total) null else page + 1,
        ) as LoadResult<Int, MarvelComic>
      }.onErrorReturn { LoadResult.Error(it) }
  }
}