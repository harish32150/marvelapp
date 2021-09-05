package com.harish.marvelapp.di

import com.harish.marvelapp.ui.main.MarvelViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
  viewModel { MarvelViewModel() }
}