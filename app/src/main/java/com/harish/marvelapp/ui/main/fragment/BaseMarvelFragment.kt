package com.harish.marvelapp.ui.main.fragment

import androidx.databinding.ViewDataBinding
import com.harish.marvelapp.ui.BaseFragment
import com.harish.marvelapp.ui.BaseViewModel

abstract class BaseMarvelFragment<B : ViewDataBinding, VM : BaseViewModel> : BaseFragment<B, VM>() {
}