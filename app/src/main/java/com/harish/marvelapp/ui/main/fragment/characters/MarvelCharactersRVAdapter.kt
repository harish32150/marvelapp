package com.harish.marvelapp.ui.main.fragment.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.harish.marvelapp.data.MarvelCharacter
import com.harish.marvelapp.databinding.ViewMarvelCharacterItemBinding
import com.harish.marvelapp.ui.main.fragment.characters.MarvelCharactersRVAdapter.MarvelCharacterItemVH

class MarvelCharactersRVAdapter :
  PagingDataAdapter<MarvelCharacter, MarvelCharacterItemVH>(DIFF_CALLBACK) {

  inner class MarvelCharacterItemVH(private val binding: ViewMarvelCharacterItemBinding) :
    ViewHolder(binding.root) {
    fun bind(character: MarvelCharacter?) {
      binding.character = character
    }
  }

  companion object {
    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MarvelCharacter>() {
      override fun areItemsTheSame(oldItem: MarvelCharacter, newItem: MarvelCharacter) =
        oldItem.id == newItem.id

      override fun areContentsTheSame(oldItem: MarvelCharacter, newItem: MarvelCharacter) =
        oldItem == newItem
    }
  }

  override fun onBindViewHolder(holder: MarvelCharacterItemVH, position: Int) {
    holder.bind(getItem(position))
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    LayoutInflater.from(parent.context)
      .let { ViewMarvelCharacterItemBinding.inflate(it, parent, false) }
      .let { MarvelCharacterItemVH(it) }
}