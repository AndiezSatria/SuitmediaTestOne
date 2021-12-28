package com.andiez.suitmediatestone.ui.main

import android.app.AlertDialog
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.andiez.suitmediatestone.R
import com.andiez.suitmediatestone.ui.base.BasePresenter
import com.andiez.suitmediatestone.utils.isPalindrome
import com.google.android.material.snackbar.Snackbar

class HomePresenter private constructor() : BasePresenter() {

    private var name: String = ""
    fun setName(name: String) {
        this.name = name
    }

    fun isNameEmpty(): Boolean =
        name.trim() == ""

    fun showSnackBar(view: View) =
        Snackbar.make(view, "Nama tidak boleh kosong.", Snackbar.LENGTH_SHORT).show()

    fun showDialogAndCheckPalindrome(fragment: HomeFragment, fragmentActivity: FragmentActivity) {
        val dialog: AlertDialog = fragmentActivity.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setTitle("IsPalindrome ?")
                setMessage(if (isPalindrome(name.trim())) R.string.is_palindrome else R.string.not_palindrome)
                setPositiveButton("Next") { _, _ ->
                    fragment.findNavController().navigate(
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

    companion object {
        private var instance: HomePresenter? = null

        fun getInstance(): HomePresenter =
            instance ?: synchronized(this) {
                instance ?: HomePresenter()
            }
    }
}