package com.andiez.suitmediatestone.ui.event

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.andiez.suitmediatestone.R
import com.andiez.suitmediatestone.databinding.FragmentEventsBinding
import com.andiez.suitmediatestone.di.Injection

class EventsFragment : Fragment() {

    private lateinit var binding: FragmentEventsBinding
    private lateinit var presenter: EventPresenter
    private fun initPresenter(): EventPresenter = Injection.provideEventPresenter(EventsFragmentArgs.fromBundle(requireArguments()).listener)

    override fun onAttach(context: Context) {
        presenter = initPresenter()
        presenter.attachLifecycle(lifecycle)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventsBinding.inflate(inflater)
        presenter.setFragment(parentFragmentManager)
        val navHostFragment = NavHostFragment.findNavController(this)
        val appBarConfiguration = AppBarConfiguration(navHostFragment.graph)
        with(binding) {
            toolbar.setupWithNavController(navHostFragment, appBarConfiguration)
            toolbar.inflateMenu(R.menu.menu_event)
            btnAdd.setOnClickListener { presenter.setFragment(parentFragmentManager) }
            val backIcon =
                AppCompatResources.getDrawable(requireContext(), R.drawable.btn_back_article_normal)
            backIcon?.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.purple_500
                ), BlendModeCompat.SRC_ATOP
            )
            toolbar.navigationIcon = backIcon
        }
        return binding.root
    }

    override fun onDestroy() {
        presenter.detachLifecycle(lifecycle)
        super.onDestroy()
    }
}