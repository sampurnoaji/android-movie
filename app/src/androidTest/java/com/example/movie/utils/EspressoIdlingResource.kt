package com.example.movie.utils

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    val idlingResource = CountingIdlingResource(RESOURCE)
}