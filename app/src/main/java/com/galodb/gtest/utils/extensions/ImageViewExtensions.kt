package com.galodb.gtest.utils.extensions

import android.widget.ImageView
import com.galodb.gtest.R
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String? = "", placeholder: Int = R.mipmap.ic_launcher) {
    if (url.isNullOrBlank()) setImageResource(placeholder)
    else Picasso.get().load(url).into(this)
}
