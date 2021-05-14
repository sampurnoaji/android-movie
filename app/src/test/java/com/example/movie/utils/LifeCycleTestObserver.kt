package com.example.movie.utils

import androidx.lifecycle.*

internal class LifeCycleTestObserver<T>(private val handler: (T) -> Unit) : Observer<T>,
    LifecycleOwner {

    private val lifecycleRegistry = LifecycleRegistry(this)

    init {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

    override fun onChanged(t: T) {
        handler(t)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }
}

fun <T> LiveData<T>.observerTest(onChangeHandler: (T) -> Unit) {
    val observer = LifeCycleTestObserver(onChangeHandler)
    observe(observer, observer)
}