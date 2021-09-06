package com.harish.marvelapp.data

import com.google.gson.annotations.SerializedName

data class MarvelComic(
  @SerializedName("id") val id: Long,
  @SerializedName("title") val title: String,
  @SerializedName("thumbnail") val thumbnail: MarvelImage
)