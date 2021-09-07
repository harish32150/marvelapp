package com.harish.marvelapp.di

import com.harish.marvelapp.repository.MarvelRepository
import org.koin.dsl.module

val repositoryModule = module {
  single { MarvelRepository(get()) }
}