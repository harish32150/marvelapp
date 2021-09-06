package com.harish.marvelapp.network

import com.harish.marvelapp.data.MarvelCharacter
import com.harish.marvelapp.data.MarvelComic
import com.harish.marvelapp.data.MarvelDataWrapper
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {
  @GET("/v1/public/characters")
  fun characters(
    @Query("nameStartsWith") query: String,
    @Query("limit") limit: Int,
    @Query("offset") offset: Int
  ): Single<MarvelDataWrapper<MarvelCharacter>>

  @GET("/v1/public/comics")
  fun comics(
    @Query("dateRange") dateRange: String,
    @Query("limit") limit: Int,
    @Query("offset") offset: Int
  ): Single<MarvelDataWrapper<MarvelComic>>
}