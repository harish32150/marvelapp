package com.harish.marvelapp.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.harish.marvelapp.BR
import com.harish.marvelapp.utils.UIUtils
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf
import kotlin.reflect.KClass

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {
  /** activity layout binding */
  protected lateinit var binding: B

  /** view model */
  protected open val viewModel: VM by lazy { getViewModel(clazz = viewModelClass()) }

  /* UI Utils */
  val uiUtils: UIUtils by inject { parametersOf(this) }

  /** provide binding layout resource id */
  @LayoutRes
  protected abstract fun layoutId(): Int

  /** provide viewModel class of type VM */
  protected abstract fun viewModelClass(): KClass<VM>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, layoutId())
    binding.apply {
      lifecycleOwner = this@BaseActivity
      setVariable(BR.viewModel, viewModel)
    }
  }
}