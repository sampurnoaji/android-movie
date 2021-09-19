package io.android.core.util

import android.widget.ImageView
import com.bumptech.glide.Glide

const val BASE_POSTER = "https://image.tmdb.org/t/p/w185"

fun ImageView.loadPoster(path: String) {
    Glide.with(context)
        .load(BASE_POSTER + path)
        .into(this)
}