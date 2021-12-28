package com.andiez.suitmediatestone.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.andiez.suitmediatestone.databinding.FragmentChooseButtonBinding
import com.andiez.suitmediatestone.di.Injection

class ChooseButtonFragment : Fragment() {

    private lateinit var binding: FragmentChooseButtonBinding
    private lateinit var presenter: ChooseButtonPresenter
    private fun initPresenter(): ChooseButtonPresenter = Injection.provideChooseButtonPresenter()

    override fun onAttach(context: Context) {
        presenter = initPresenter()
        presenter.attachLifecycle(lifecycle)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseButtonBinding.inflate(inflater)
        presenter.setName(ChooseButtonFragmentArgs.fromBundle(requireArguments()).name)
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            presenter = this@ChooseButtonFragment.presenter
            btnGuest.setOnClickListener {
                this@ChooseButtonFragment.presenter.goToGuestPage(this@ChooseButtonFragment)
            }
            btnEvent.setOnClickListener {
                this@ChooseButtonFragment.presenter.goToEventPage(this@ChooseButtonFragment)
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        presenter.detachLifecycle(lifecycle)
        super.onDestroy()
    }
}