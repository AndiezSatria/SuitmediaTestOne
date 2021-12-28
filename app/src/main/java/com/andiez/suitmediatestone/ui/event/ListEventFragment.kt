package com.andiez.suitmediatestone.ui.event

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andiez.suitmediatestone.databinding.FragmentListEventBinding
import com.andiez.suitmediatestone.di.Injection
import com.andiez.suitmediatestone.helper.EventChooseListener

class ListEventFragment private constructor(private val listener: EventChooseListener) :
    Fragment() {

    private lateinit var binding: FragmentListEventBinding
    private lateinit var presenter: EventPresenter
    private fun initPresenter(): EventPresenter = Injection.provideEventPresenter(listener)

    override fun onAttach(context: Context) {
        presenter = initPresenter()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListEventBinding.inflate(inflater)
        with(binding) {
            tvEmpty.visibility = if (presenter.events.isEmpty()) View.VISIBLE else View.GONE
            presenter.settingAdapterRecyclerview(rvEvent, requireParentFragment())
        }
        return binding.root
    }

    companion object {
        fun getInstance(listener: EventChooseListener): ListEventFragment =
            ListEventFragment(listener)
    }
}