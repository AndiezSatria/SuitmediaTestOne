package com.andiez.suitmediatestone.ui.main

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import androidx.navigation.fragment.findNavController
import com.andiez.suitmediatestone.R
import com.andiez.suitmediatestone.databinding.FragmentHomeBinding
import com.andiez.suitmediatestone.di.Injection
import com.andiez.suitmediatestone.ui.base.BaseFragment
import com.andiez.suitmediatestone.utils.isPalindrome
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var presenter: HomePresenter
    private fun initPresenter(): HomePresenter = Injection.provideHomePresenter()

    override fun onAttach(context: Context) {
        presenter = initPresenter()
        presenter.attachLifecycle(lifecycle)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        with(binding) {
            buttonNext.setOnClickListener {
                presenter.setName(this.tfName.editText?.text.toString())
                if (presenter.isNameEmpty()) {
                    presenter.showSnackBar(this.root)
                } else {
                    presenter.showDialogAndCheckPalindrome(this@HomeFragment, requireActivity())
                }
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        presenter.detachLifecycle(lifecycle)
        super.onDestroy()
    }
}