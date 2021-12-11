package com.andiez.suitmediatestone.ui.main

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.andiez.suitmediatestone.R
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
//                    Toast.makeText(
//                        requireContext(),
//                        if (isPalindrome(name.trim())) "isPalindrome" else "Not Palindrome",
//                        Toast.LENGTH_SHORT
//                    ).show()
                    val dialog: AlertDialog = requireActivity().let {
                        val builder = AlertDialog.Builder(it)
                        builder.apply {
                            setTitle("IsPalindrome ?")
                            setMessage(if (isPalindrome(name.trim())) R.string.is_palindrome else R.string.not_palindrome)
                            setPositiveButton("Next") { _, _ ->
                                this@HomeFragment.findNavController().navigate(
                                    HomeFragmentDirections.actionHomeFragmentToChooseButtonFragment(
                                        name.trim()
                                    )
                                )
                            }
                        }
                        builder.create()
                    }
                    dialog.show()
                }
            }
        }
        return binding.root
    }
}