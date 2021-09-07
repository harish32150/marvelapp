package com.harish.marvelapp.utils

import android.content.Context
import kotlin.properties.Delegates

class SearchHistoryPrefs(private val context: Context) {
  private val sharedPrefs by lazy {
    context.getSharedPreferences("search_history", Context.MODE_PRIVATE)
  }

//  var searchItems by Delegates.observable(sharedPrefs.getStringSet("search_items", setOf())) {_, _ov, _nv ->
//    if(_nv != _ov) {
//      sharedPrefs.edit().putStringSet("search_items", _nv).apply()
//    }
//  }

  fun searchItems() = sharedPrefs.getStringSet("search_items", setOf())

  fun addSearchItem(item: String) =
    (searchItems()?.toMutableSet()?.apply { add(item) } ?: setOf(item))
      .let { sharedPrefs.edit().putStringSet("search_items", it).apply() }
}