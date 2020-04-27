package com.darkknight.base.util

import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

/**
 *
 * Created by Rooparsh Kalia on 12/04/20
 *
 **/

@BindingAdapter("errorText")
fun setError(textInputLayout: TextInputLayout, strOrResId: Any?) {
    if (strOrResId is Int) {
        textInputLayout.error = textInputLayout.context.getString((strOrResId as Int?)!!)
    } else {
        textInputLayout.error = strOrResId as String?
    }
}

@BindingAdapter("onFocus")
fun bindFocusChange(
    editText: EditText,
    onFocusChangeListener: View.OnFocusChangeListener?
) {
    if (editText.onFocusChangeListener == null) {
        editText.onFocusChangeListener = onFocusChangeListener
    }
}