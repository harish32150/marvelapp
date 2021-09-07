package com.harish.marvelapp

import android.app.Application
import com.harish.marvelapp.di.activityModule
import com.harish.marvelapp.di.networkModule
import com.harish.marvelapp.di.repositoryModule
import com.harish.marvelapp.di.viewModelModule
import com.harish.marvelapp.utils.SearchHistoryPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MarvelApp : Application() {
  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidLogger()
      androidContext(this@MarvelApp)
      modules(
        networkModule,
        repositoryModule,
        viewModelModule,
        activityModule,
        module {
          single { SearchHistoryPrefs(androidContext()) }
        }
      )
    }
  }
}