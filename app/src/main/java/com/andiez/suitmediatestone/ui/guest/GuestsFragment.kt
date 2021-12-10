package com.andiez.suitmediatestone.ui.guest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.andiez.suitmediatestone.databinding.FragmentGuestsBinding
import com.andiez.suitmediatestone.model.local.GuestEntity
import com.andiez.suitmediatestone.ui.SharedMainViewModel
import com.andiez.suitmediatestone.utils.isPrime
import java.text.SimpleDateFormat
import java.util.*

class GuestsFragment : Fragment() {

    private lateinit var binding: FragmentGuestsBinding
    private val viewModel: SharedMainViewModel by activityViewModels()
    private lateinit var adapter: GuestAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGuestsBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        val navHostFragment = NavHostFragment.findNavController(this)
        val appBarConfiguration = AppBarConfiguration(navHostFragment.graph)
        adapter = GuestAdapter(object : GuestAdapter.GuestItemHandler {
            override fun onClick(item: GuestEntity, position: Int) {
                val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val date = formatter.parse(item.birthdate)
                val calendar = Calendar.getInstance()
                calendar.time = date!!
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val month = calendar.get(Calendar.MONTH) + 1
                var text = ""
                text += when {
                    (day % 2 == 0 && day % 3 == 0) ->
                        "iOS"
                    (day % 2 == 0) ->
                        "blackberry"
                    (day % 3 == 0) ->
                        "android"
                    else -> "feature phone"
                }
                text += ", ${if (isPrime(month)) "isPrime" else "not Prime"}"
                Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT)
                    .show()
                viewModel.setSelectedGuest(item)
                this@GuestsFragment.findNavController().popBackStack()
            }

        })
        viewModel.getGuests().observe(viewLifecycleOwner)
        { guestList ->
            if (guestList != null) {
                adapter.setListNotes(guestList)
                binding.tvEmpty.visibility =
                    if (guestList.isEmpty()) View.VISIBLE else View.GONE
            }
        }
        with(binding)
        {
            viewModel = this@GuestsFragment.viewModel
            toolbar.setupWithNavController(navHostFragment, appBarConfiguration)
            rvGuest.setHasFixedSize(true)
            rvGuest.adapter = adapter
        }

        return binding.root
    }
}