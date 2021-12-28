package com.andiez.suitmediatestone.ui.guest

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.andiez.suitmediatestone.databinding.FragmentGuestsBinding
import com.andiez.suitmediatestone.di.Injection
import java.util.*

class GuestsFragment : Fragment() {

    private lateinit var binding: FragmentGuestsBinding
    private lateinit var presenter: GuestPresenter
    private fun initPresenter(): GuestPresenter =
        Injection.provideGuestPresenter(GuestsFragmentArgs.fromBundle(requireArguments()).listener)

    override fun onAttach(context: Context) {
        presenter = initPresenter()
        presenter.attachLifecycle(lifecycle)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGuestsBinding.inflate(inflater)

        val navHostFragment = NavHostFragment.findNavController(this)
        val appBarConfiguration = AppBarConfiguration(navHostFragment.graph)
        with(binding) {
            presenter.getGuests().observe(
                viewLifecycleOwner,
                presenter.getObserver(
                    refreshLayout,
                    rvGuest,
                    requireActivity(),
                    this@GuestsFragment
                )
            )
            toolbar.setupWithNavController(navHostFragment, appBarConfiguration)
            refreshLayout.setOnRefreshListener {
                presenter.getGuests().observe(
                    viewLifecycleOwner,
                    presenter.getObserver(
                        refreshLayout,
                        rvGuest,
                        requireActivity(),
                        this@GuestsFragment
                    )
                )
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        presenter.detachLifecycle(lifecycle)
        super.onDestroy()
    }
}