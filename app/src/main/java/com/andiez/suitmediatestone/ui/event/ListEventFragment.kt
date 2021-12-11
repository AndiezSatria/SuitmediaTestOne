package com.andiez.suitmediatestone.ui.event

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.andiez.suitmediatestone.R
import com.andiez.suitmediatestone.databinding.FragmentListEventBinding
import com.andiez.suitmediatestone.model.local.EventEntity
import com.andiez.suitmediatestone.ui.SharedMainViewModel
import com.andiez.suitmediatestone.viewmodel.ViewModelFactory

class ListEventFragment : Fragment() {

    private lateinit var binding: FragmentListEventBinding
    private val viewModel: SharedMainViewModel by activityViewModels {
        ViewModelFactory.getInstance(requireActivity().application)
    }
    private lateinit var adapter: EventsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListEventBinding.inflate(inflater)
        adapter = EventsAdapter(object : EventsAdapter.EventItemHandler {
            override fun onItemClick(position: Int, item: EventEntity) {
                viewModel.setSelectedEvent(item)
                requireParentFragment().findNavController().popBackStack()
            }
        })
        adapter.setListNotes(viewModel.events)
        with(binding) {
            tvEmpty.visibility = if (viewModel.events.isEmpty()) View.VISIBLE else View.GONE
            rvEvent.setHasFixedSize(true)
            rvEvent.adapter = adapter
        }
        return binding.root
    }

    companion object {
        fun getInstance(): ListEventFragment = ListEventFragment()
    }
}