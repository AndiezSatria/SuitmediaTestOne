package com.andiez.suitmediatestone.ui.guest

import android.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.andiez.suitmediatestone.domain.usecase.TestUseCase
import com.andiez.suitmediatestone.ui.base.BasePresenter
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.andiez.suitmediatestone.helper.GuestSelectListener
import com.andiez.suitmediatestone.model.local.GuestEntity
import com.andiez.suitmediatestone.source.Resource
import com.andiez.suitmediatestone.utils.isPrime
import java.text.SimpleDateFormat
import java.util.*

class GuestPresenter private constructor(
    useCase: TestUseCase,
    private val listener: GuestSelectListener
) : BasePresenter() {
    private val _guests = useCase.getAllGuest().asLiveData()
    fun getGuests(): LiveData<Resource<List<GuestEntity>>> = _guests

    private fun setupAdapter(
        data: List<GuestEntity>?,
        rv: RecyclerView,
        fragmentActivity: FragmentActivity,
        fragment: GuestsFragment
    ) {
        val adapter = GuestAdapter(object : GuestAdapter.GuestItemHandler {
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
                showDialog(fragment, fragmentActivity, text)
                listener.onGuestSelect(item)
            }

        })
        adapter.setListNotes(data)
        rv.setHasFixedSize(true)
        rv.adapter = adapter
    }

    private fun showDialog(
        fragment: GuestsFragment,
        fragmentActivity: FragmentActivity,
        text: String
    ) {
        val dialog: AlertDialog = fragmentActivity.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setTitle("Result")
                setMessage(text)
                setPositiveButton("Back") { _, _ ->
                    fragment.findNavController().popBackStack()
                }
            }
            builder.create()
        }
        dialog.show()
    }

    fun getObserver(
        refreshLayout: SwipeRefreshLayout,
        rv: RecyclerView,
        fragmentActivity: FragmentActivity,
        fragment: GuestsFragment
    ): Observer<Resource<List<GuestEntity>>> =
        Observer { guest ->
            when (guest) {
                is Resource.Error -> {
                    refreshLayout.isRefreshing = false
                }
                is Resource.Loading -> {
                    refreshLayout.isRefreshing = true
                }
                is Resource.Success -> {
                    refreshLayout.isRefreshing = false
                    setupAdapter(guest.data, rv, fragmentActivity, fragment)
                }
            }
        }

    companion object {
        @Volatile
        private var instance: GuestPresenter? = null

        fun getInstance(useCase: TestUseCase, listener: GuestSelectListener): GuestPresenter =
            instance ?: synchronized(this) {
                instance ?: GuestPresenter(useCase, listener)
            }
    }
}