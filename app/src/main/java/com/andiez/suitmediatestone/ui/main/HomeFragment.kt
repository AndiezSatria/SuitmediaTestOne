package com.andiez.suitmediatestone.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andiez.suitmediatestone.databinding.FragmentHomeBinding
import com.andiez.suitmediatestone.di.Injection

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