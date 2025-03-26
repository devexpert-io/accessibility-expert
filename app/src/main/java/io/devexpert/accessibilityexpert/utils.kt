package io.devexpert.accessibilityexpert

import android.content.Context
import android.widget.Toast

fun Context.toast(resId: Int) {
    Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()
}