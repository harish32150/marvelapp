package com.harish.marvelapp.data

import com.google.gson.annotations.SerializedName

data class MarvelCharacter(
  @SerializedName("id") val id: Long,
  @SerializedName("name") val name: String,
  @SerializedName("thumbnail") val thumbnail: MarvelImage,
)