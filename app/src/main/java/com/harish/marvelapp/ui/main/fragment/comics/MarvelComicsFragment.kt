package com.harish.marvelapp.ui.main.fragment.comics

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.harish.marvelapp.R
import com.harish.marvelapp.databinding.FragmentMarvelComicsBinding
import com.harish.marvelapp.ui.main.MarvelActivity
import com.harish.marvelapp.ui.main.MarvelViewModel
import com.harish.marvelapp.ui.main.fragment.BaseMarvelFragment
import com.harish.marvelapp.ui.main.fragment.MarvelFooterAdapter
import com.harish.marvelapp.ui.main.fragment.comics.MarvelComicFilter.ThisWeek

class MarvelComicsFragment : BaseMarvelFragment<FragmentMarvelComicsBinding, MarvelViewModel>() {
  override fun layoutId() = R.layout.fragment_marvel_comics

  override fun viewModelClass() = MarvelViewModel::class

  private val comicsAdapter by lazy { MarvelComicsRVAdapter() }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.rv.apply {
      layoutManager = LinearLayoutManager(this@MarvelComicsFragment.requireContext())
      adapter = comicsAdapter.withLoadStateFooter(MarvelFooterAdapter {
        comicsAdapter.retry()
      })
    }

    viewModel.comics.observe(activity as MarvelActivity, {
      comicsAdapter.submitData(lifecycle, it)
    })

    setupFilterSpinner()

    filter(ThisWeek)
  }

  private fun setupFilterSpinner() {
    ArrayAdapter(
      this@MarvelComicsFragment.requireContext(),
      android.R.layout.simple_spinner_item,
      MarvelComicFilter.values().map { it.filterLabel })
      .also { _adapter ->
        _adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerFilter.adapter = _adapter
      }
    binding.spinnerFilter.onItemSelectedListener = object : OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        val _filter = parent.getItemAtPosition(pos) as String
        MarvelComicFilter.fromLabel(_filter)?.let { filter(it) }
      }

      override fun onNothingSelected(p0: AdapterView<*>?) {

      }
    }
  }

  private fun filter(filter: MarvelComicFilter) {
    viewModel.fetchComics(filter.dateRange())
  }
}