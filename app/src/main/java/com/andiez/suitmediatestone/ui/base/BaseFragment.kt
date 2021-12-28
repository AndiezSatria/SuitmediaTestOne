package com.andiez.suitmediatestone.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleRegistry

abstract class BaseFragment<p : BasePresenter> : Fragment() {
    private val lifecycleRegistry = LifecycleRegistry(requireActivity())

    lateinit var presenter: p

    protected abstract fun initPresenter(): p

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = initPresenter()
        presenter.attachLifecycle(lifecycleRegistry)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachLifecycle(lifecycleRegistry)
    }
}