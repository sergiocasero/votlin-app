package com.votlin.android.extensions

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * ViewExtensions.
 */
fun ImageView.load(url: String, circleCrop: Boolean = false) {
    var options = RequestOptions()

    if (circleCrop) {
        options = RequestOptions.circleCropTransform()
    }

    Glide.with(this)
            .load(url)
            .apply(options)
            .into(this)
}

fun ImageView.load(url: String) {
    Glide.with(this)
            .load(url)
            .into(this)
}

/**
 * View
 * */
fun View.hideMe(gone: Boolean = true) {
    this.visibility = if (gone) View.GONE else View.INVISIBLE
}

fun View.showMe() {
    this.visibility = View.VISIBLE
}