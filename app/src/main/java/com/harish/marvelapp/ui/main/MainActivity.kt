package com.harish.marvelapp.ui.main

import com.harish.marvelapp.ui.BaseActivity
import com.harish.marvelapp.R
import com.harish.marvelapp.databinding.ActivityMainBinding

class MainActivity: BaseActivity<ActivityMainBinding, MainViewModel>() {
  override fun layoutId() = R.layout.activity_main

  override fun viewModelClass() = MainViewModel::class
}