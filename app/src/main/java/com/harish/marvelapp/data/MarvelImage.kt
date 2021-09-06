package com.harish.marvelapp.data

import com.google.gson.annotations.SerializedName

data class MarvelImage(
  @SerializedName("path") val path: String,
  @SerializedName("extension") val extension: String
)