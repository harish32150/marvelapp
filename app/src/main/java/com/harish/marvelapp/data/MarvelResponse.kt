package com.harish.marvelapp.data

import com.google.gson.annotations.SerializedName

data class MarvelDataWrapper<T : Any>(
  @SerializedName("code") val code: Int,
  @SerializedName("status") val status: String,
  @SerializedName("data") val data: MarvelDataContainer<T>
)

data class MarvelDataContainer<T : Any>(
  @SerializedName("offset") val offset: Int,
  @SerializedName("limit") val limit: Int,
  @SerializedName("total") val total: Int,
  @SerializedName("count") val count: Int,
  @SerializedName("results") val results: List<T>
)