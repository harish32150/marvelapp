package com.harish.marvelapp.ui.main.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.harish.marvelapp.databinding.ViewMarvelFooterItemBinding
import com.harish.marvelapp.ui.main.fragment.MarvelFooterAdapter.MarvelFooterVH

class MarvelFooterAdapter(private val retryAction: () -> Unit) :
  LoadStateAdapter<MarvelFooterVH>() {

  inner class MarvelFooterVH(private val binding: ViewMarvelFooterItemBinding) :
    ViewHolder(binding.root) {

    fun bind(loadState: LoadState, retryAction: () -> Unit) {
      binding.btnRetry.setOnClickListener { retryAction.invoke() }
      binding.textError.text = if (loadState is LoadState.Error)
        loadState.error.message
      else if (loadState is LoadState.NotLoading && loadState.endOfPaginationReached)
        "You have reached till end"
      else ""

      binding.apply {
        textError.isVisible =
          loadState !is LoadState.Loading || (loadState is LoadState.NotLoading && loadState.endOfPaginationReached)
        progress.isVisible = loadState is LoadState.Loading
        btnRetry.isVisible = loadState !is LoadState.Loading
      }
    }
  }

  override fun onBindViewHolder(holder: MarvelFooterVH, loadState: LoadState) {
    holder.bind(loadState, retryAction)
  }

  override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): MarvelFooterVH =
    LayoutInflater.from(parent.context)
      .let { _inflater -> ViewMarvelFooterItemBinding.inflate(_inflater, parent, false) }
      .let { MarvelFooterVH(it) }
}