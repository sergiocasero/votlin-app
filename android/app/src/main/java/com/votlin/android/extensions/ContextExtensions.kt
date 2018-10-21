package com.votlin.android.extensions

import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * ContextExtensions
 */

/**
 * Context
 * */
fun Context.toast(text: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, text, length).show()
}

fun Context.toast(textId: Int, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, textId, length).show()
}

fun Context.toPx(dp: Int): Int = resources.getDimensionPixelSize(dp)


/**
 * Fragments
 * */
fun Fragment.toast(text: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(activity, text, length).show()
}

fun Fragment.toast(textId: Int, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(activity, textId, length).show()
}