package com.harish.marvelapp.ui.main

import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.harish.marvelapp.R
import com.harish.marvelapp.databinding.ActivityMainBinding
import com.harish.marvelapp.ui.BaseActivity
import com.harish.marvelapp.ui.main.fragment.MarvelFragmentsAdapter

class MarvelActivity : BaseActivity<ActivityMainBinding, MarvelViewModel>() {
  override fun layoutId() = R.layout.activity_main

  override fun viewModelClass() = MarvelViewModel::class

  private val fragmentsAdapter by lazy { MarvelFragmentsAdapter(this@MarvelActivity) }

  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)

    binding.pager.apply {
      adapter = fragmentsAdapter
      TabLayoutMediator(binding.tabLayout, this) { tab, position ->
        tab.text = fragmentsAdapter.getPageTitle(position)
      }.attach()
    }
  }
}