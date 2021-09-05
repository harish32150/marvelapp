package com.harish.marvelapp.ui

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
  private val compositeDisposable by lazy { CompositeDisposable() }

  override fun onCleared() {
    super.onCleared()
    if (!compositeDisposable.isDisposed && compositeDisposable.size() > 0) {
      compositeDisposable.dispose()
    }
  }

  protected fun Disposable.queue() = compositeDisposable.add(this)
}