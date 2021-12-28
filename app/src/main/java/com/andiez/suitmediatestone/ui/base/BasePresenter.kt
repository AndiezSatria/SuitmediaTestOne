package com.andiez.suitmediatestone.ui.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver

abstract class BasePresenter : LifecycleObserver {
    fun attachLifecycle(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }

    fun detachLifecycle(lifecycle: Lifecycle) {
        lifecycle.removeObserver(this)
    }

}