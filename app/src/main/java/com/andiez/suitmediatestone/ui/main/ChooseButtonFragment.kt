package com.andiez.suitmediatestone.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.andiez.suitmediatestone.databinding.FragmentChooseButtonBinding
import com.andiez.suitmediatestone.ui.SharedMainViewModel

class ChooseButtonFragment : Fragment() {

    private lateinit var binding: FragmentChooseButtonBinding
    private val viewModel: SharedMainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseButtonBinding.inflate(inflater)

        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@ChooseButtonFragment.viewModel
            tvName.text = ChooseButtonFragmentArgs.fromBundle(requireArguments()).name
            btnGuest.setOnClickListener {
                this@ChooseButtonFragment.findNavController().navigate(
                    ChooseButtonFragmentDirections.actionChooseButtonFragmentToGuestsFragment()
                )
            }
            btnEvent.setOnClickListener {
                this@ChooseButtonFragment.findNavController().navigate(
                    ChooseButtonFragmentDirections.actionChooseButtonFragmentToEventsFragment()
                )
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clear()
    }
}