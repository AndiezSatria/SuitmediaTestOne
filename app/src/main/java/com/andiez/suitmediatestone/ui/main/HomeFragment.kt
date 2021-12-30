package com.andiez.suitmediatestone.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andiez.suitmediatestone.databinding.FragmentHomeBinding
import com.andiez.suitmediatestone.di.Injection
import com.firebase.ui.auth.AuthUI

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
            lifecycleOwner = viewLifecycleOwner
            buttonNext.setOnClickListener {
                presenter.setName(this.tfName.editText?.text.toString())
                if (presenter.isNameEmpty()) {
                    presenter.showSnackBar(this.root)
                } else {
                    presenter.showDialogAndCheckPalindrome(this@HomeFragment, requireActivity())
                }
            }
            buttonLogin.setOnClickListener { startLogin() }
        }
        presenter.user.observe(viewLifecycleOwner) { user ->
            presenter.updateUI(binding.tvWelcome, binding.buttonLogin, user, requireContext())
        }
        return binding.root
    }

    override fun onDestroy() {
        presenter.detachLifecycle(lifecycle)
        super.onDestroy()
    }

    private fun startLogin() {
        if (presenter.isSignedIn) {
            AuthUI.getInstance().signOut(requireContext())
            presenter.isSignedIn = false
            return
        }
        val providers = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())
        val intent =
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers)
                .build()
        presenter.isSignedIn = true
        startActivityForResult(intent, SIGN_IN_REQUEST_CODE)
    }

    companion object {
        private const val SIGN_IN_REQUEST_CODE = 101
    }
}