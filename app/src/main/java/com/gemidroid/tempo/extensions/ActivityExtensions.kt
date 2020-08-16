package com.gemidroid.tempo.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Activity.showToast(duration: Int, message: String) {
    Toast.makeText(this, message, duration).show()
}

fun View.showSnackBar(
    duration: Int,
    message: String,
    actionName: String = "Ok",
    actionJob: () -> Unit
) {
    Snackbar.make(this, message, duration)
        .setAction(actionName) {
            actionJob.invoke()
        }
        .show()
}

fun Activity.hideKeyboard() {
    val inputManager: InputMethodManager =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(
        this.currentFocus!!.windowToken,
        InputMethodManager.HIDE_NOT_ALWAYS
    )
}

