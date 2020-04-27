package com.darkknight.base.util

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.view.View
import android.widget.Toast
import androidx.annotation.AttrRes
import com.google.android.material.textfield.TextInputLayout


/**
 *
 * Created by Rooparsh Kalia on 08/03/20
 *
 **/
fun TextInputLayout.getText(): String {
    return this.editText?.text.toString().trim()
}

fun Context.showToast(message: String, time: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, time).show()
}

fun Context.getThemeColor(@AttrRes attrResId: Int): Int {
    val a: TypedArray = this.obtainStyledAttributes(null, intArrayOf(attrResId))
    return try {
        a.getColor(0, Color.MAGENTA)
    } finally {
        a.recycle()
    }
}

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun String.splitName(): Pair<String, String> {
    val firstName: String
    val lastName: String
    val indexOfLastSpace = this.lastIndexOf(SPACE)
    if (this.split("\\s".toRegex()).isNotEmpty() && indexOfLastSpace != -1) {
        firstName = this.substring(0, indexOfLastSpace)
        lastName = this.substring(indexOfLastSpace + 1)
    } else {
        firstName = this
        lastName = ""
    }

    return Pair(firstName, lastName)
}