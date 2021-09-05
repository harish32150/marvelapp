package com.harish.marvelapp.utils

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UIUtils(private val activity: AppCompatActivity) {

  /* activity root view */
  private val activityRoot: ViewGroup by lazy {
    activity.findViewById<ViewGroup>(android.R.id.content)
  }

  fun toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(activity, msg, duration).show()
  }

  /**
   * Toggle soft keyboard visibility
   *
   * @param hide [Boolean] flag to hide/show soft keyboard, default is set to hide keyboard
   */
  fun toggleKeyboard(hide: Boolean = true) {
    if (activity.isFinishing) return
    val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    (activity.findViewById(android.R.id.content) as View?)?.windowToken?.let {
      when (hide) {
        true -> imm.hideSoftInputFromWindow(it, 0)
        false -> imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
      }
    }
  }
}