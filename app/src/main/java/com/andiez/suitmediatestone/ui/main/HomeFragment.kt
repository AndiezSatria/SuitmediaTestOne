package com.andiez.suitmediatestone.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.andiez.suitmediatestone.databinding.FragmentHomeBinding
import com.andiez.suitmediatestone.utils.isPalindrome
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        with(binding) {
            buttonNext.setOnClickListener {
                val name = binding.tfName.editText?.text.toString()
                if (name.trim() == "")
                    Snackbar.make(binding.root, "Nama tidak boleh kosong.", Snackbar.LENGTH_SHORT)
                        .show()
                else {
                    Toast.makeText(
                        requireContext(),
                        if (isPalindrome(name.trim())) "isPalindrome" else "Not Palindrome",
                        Toast.LENGTH_SHORT
                    ).show()
                    this@HomeFragment.findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToChooseButtonFragment(name.trim())
                    )
                }
            }
        }
        return binding.root
    }
}