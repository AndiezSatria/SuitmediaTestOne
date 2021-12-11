package com.andiez.suitmediatestone.ui.event

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

class EventsFragment : Fragment() {

    private lateinit var binding: FragmentEventsBinding
    private var fragmentPosition = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventsBinding.inflate(inflater)
        setFragment()
        val navHostFragment = NavHostFragment.findNavController(this)
        val appBarConfiguration = AppBarConfiguration(navHostFragment.graph)
        with(binding) {
            toolbar.setupWithNavController(navHostFragment, appBarConfiguration)
            toolbar.inflateMenu(R.menu.menu_event)
            btnAdd.setOnClickListener { setFragment() }
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

    private fun setFragment() {
        val fragment = when (fragmentPosition) {
            1 -> {
                fragmentPosition = 2
                ListEventFragment.getInstance()
            }
            else -> {
                fragmentPosition = 1
                EventMapFragment.newInstance()
            }
        }
        val manager = parentFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}