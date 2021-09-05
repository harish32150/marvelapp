package com.harish.marvelapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.harish.marvelapp.BR
import com.harish.marvelapp.utils.UIUtils
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel
import org.koin.core.parameter.parametersOf
import kotlin.reflect.KClass

abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel> : Fragment() {
  /** activity layout binding */
  protected lateinit var binding: B

  /** view model */
  protected open val viewModel: VM by lazy { getSharedViewModel(clazz = viewModelClass()) }

  /** provide binding layout resource id */
  @LayoutRes
  protected abstract fun layoutId(): Int

  /** provide viewModel class of type VM */
  protected abstract fun viewModelClass(): KClass<VM>

  /* UI Utils */
  val uiUtils: UIUtils by inject { parametersOf(activity) }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
    binding.apply {
      lifecycleOwner = this@BaseFragment
      setVariable(BR.viewModel, viewModel)
    }
    return binding.root
  }
}