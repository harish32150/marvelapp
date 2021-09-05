package com.harish.marvelapp.di

import androidx.appcompat.app.AppCompatActivity
import com.harish.marvelapp.utils.UIUtils
import org.koin.dsl.module

val activityModule = module {
  factory { (activity: AppCompatActivity) ->
    UIUtils(activity)
  }
}