package com.harish.marvelapp.ui.main.fragment.comics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.harish.marvelapp.data.MarvelComic
import com.harish.marvelapp.databinding.ViewMarvelComicItemBinding
import com.harish.marvelapp.ui.main.fragment.comics.MarvelComicsRVAdapter.MarvelComicItemVH

class MarvelComicsRVAdapter : PagingDataAdapter<MarvelComic, MarvelComicItemVH>(DIFF_CALLBACK) {
  inner class MarvelComicItemVH(private val binding: ViewMarvelComicItemBinding) :
    ViewHolder(binding.root) {
    fun bind(comic: MarvelComic?) {
      binding.comic = comic
    }
  }

  companion object {
    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MarvelComic>() {
      override fun areItemsTheSame(oldItem: MarvelComic, newItem: MarvelComic) =
        oldItem.id == newItem.id

      override fun areContentsTheSame(oldItem: MarvelComic, newItem: MarvelComic) =
        oldItem == newItem
    }
  }

  override fun onBindViewHolder(holder: MarvelComicItemVH, position: Int) {
    holder.bind(getItem(position))
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    LayoutInflater.from(parent.context)
      .let { ViewMarvelComicItemBinding.inflate(it, parent, false) }
      .let { MarvelComicItemVH(it) }
}