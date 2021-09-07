package com.harish.marvelapp.utils.extensions

import android.view.inputmethod.EditorInfo
import android.widget.EditText

/**
 * Action done
 */
fun EditText.actionDone(
  actionId: Int = EditorInfo.IME_ACTION_DONE,
  action: (String) -> Unit
) {
  setOnEditorActionListener { _, _actionId, _ ->
    if (_actionId == actionId) {
      action(text.toString())
      true
    } else {
      false
    }
  }
}