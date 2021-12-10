package com.andiez.suitmediatestone.ui.event

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.andiez.suitmediatestone.databinding.FragmentEventsBinding
import com.andiez.suitmediatestone.model.local.EventEntity
import com.andiez.suitmediatestone.ui.SharedMainViewModel

class EventsFragment : Fragment() {

    private lateinit var binding: FragmentEventsBinding
    private val viewModel: SharedMainViewModel by activityViewModels()
    private lateinit var adapter: EventsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventsBinding.inflate(inflater)
        val navHostFragment = NavHostFragment.findNavController(this)
        val appBarConfiguration = AppBarConfiguration(navHostFragment.graph)
        adapter = EventsAdapter(object : EventsAdapter.EventItemHandler {
            override fun onItemClick(position: Int, item: EventEntity) {
                viewModel.setSelectedEvent(item)
                this@EventsFragment.findNavController().popBackStack()
            }
        })
        adapter.setListNotes(viewModel.events)
        with(binding) {
            toolbar.setupWithNavController(navHostFragment, appBarConfiguration)
            rvEvent.setHasFixedSize(true)
            rvEvent.adapter = adapter
            rvEvent.addItemDecoration(
                DividerItemDecoration(
                    rvEvent.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
        return binding.root
    }

}