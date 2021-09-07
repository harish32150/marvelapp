package com.harish.marvelapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.harish.marvelapp.R
import com.harish.marvelapp.data.MarvelImage

@BindingAdapter("marvel_image")
fun setImageSrc(
  view: ImageView,
  image: MarvelImage
) {
  Glide
    .with(view.context)
    .load(image.url())
    .centerCrop()
    .placeholder(R.drawable.ic_baseline_image_24)
    .into(view)
}