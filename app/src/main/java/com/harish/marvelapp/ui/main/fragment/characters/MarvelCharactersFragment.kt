package com.harish.marvelapp.ui.main.fragment.characters

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.harish.marvelapp.R
import com.harish.marvelapp.databinding.FragmentMarvelCharactersBinding
import com.harish.marvelapp.ui.main.MarvelActivity
import com.harish.marvelapp.ui.main.MarvelViewModel
import com.harish.marvelapp.ui.main.fragment.BaseMarvelFragment
import com.harish.marvelapp.ui.main.fragment.MarvelFooterAdapter
import com.harish.marvelapp.utils.SearchHistoryPrefs
import com.harish.marvelapp.utils.extensions.actionDone
import com.harish.marvelapp.utils.extensions.visible
import org.koin.android.ext.android.inject

class MarvelCharactersFragment :
  BaseMarvelFragment<FragmentMarvelCharactersBinding, MarvelViewModel>() {
  override fun layoutId() = R.layout.fragment_marvel_characters

  override fun viewModelClass() = MarvelViewModel::class

  private val searchPrefs: SearchHistoryPrefs by inject()

  private val charactersAdapter by lazy { MarvelCharactersRVAdapter() }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.ibtnSearch.setOnClickListener { searchImages(binding.editSearch.text.toString()) }
    binding.editSearch.actionDone {
      searchImages(it)
    }
    setupList()
    refreshHistory()
  }

  private fun setupList() {
    binding.rv.apply {
      layoutManager = LinearLayoutManager(this@MarvelCharactersFragment.requireContext())
      adapter = charactersAdapter.withLoadStateFooter(MarvelFooterAdapter {
        charactersAdapter.retry()
      })
    }

    viewModel.characters.observe(activity as MarvelActivity, {
      charactersAdapter.submitData(lifecycle, it)
    })

    charactersAdapter.addLoadStateListener { _loadStates ->
      binding.srl.isRefreshing = _loadStates.refresh is LoadState.Loading
      binding.editSearch.isEnabled = _loadStates.refresh !is LoadState.Loading
      binding.ibtnSearch.isEnabled = _loadStates.refresh !is LoadState.Loading
      binding.viewError.container.visibility = if(_loadStates.refresh is LoadState.Error) View.VISIBLE else View.GONE
      if (_loadStates.refresh is LoadState.Error) {
        binding.viewError.textError.text = (_loadStates.refresh as LoadState.Error).error.message
        binding.viewError.btnRetry.setOnClickListener { charactersAdapter.retry() }
      }
    }

    binding.srl.setOnRefreshListener { searchImages(binding.editSearch.text.toString()) }
  }

  private fun refreshHistory() {
    binding.editSearch.apply {
      ArrayAdapter(
        this@MarvelCharactersFragment.requireContext(),
        android.R.layout.simple_spinner_item,
        searchPrefs.searchItems()?.toList() ?: listOf()
      ).also {
        it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        setAdapter(it)
      }
      onItemSelectedListener = object : OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
          val _query = parent.getItemAtPosition(pos) as String
          searchImages(_query)
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {

        }
      }
    }
  }

  /* search images */
  private fun searchImages(query: String) {
    if (query.isNullOrEmpty()) {
      uiUtils.toast("Enter a query")
      return
    }
    binding.editSearch.clearFocus()
    uiUtils.apply {
      toggleKeyboard(hide = true)
    }
    viewModel.searchCharacters(query)
    searchPrefs.addSearchItem(query)
    refreshHistory()
  }
}