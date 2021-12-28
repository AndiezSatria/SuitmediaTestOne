package com.andiez.suitmediatestone.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.andiez.suitmediatestone.helper.EventSelectListener
import com.andiez.suitmediatestone.helper.GuestSelectListener
import com.andiez.suitmediatestone.model.local.EventEntity
import com.andiez.suitmediatestone.model.local.GuestEntity
import com.andiez.suitmediatestone.ui.base.BasePresenter

class ChooseButtonPresenter private constructor() : BasePresenter() {
    var chosenName: String = ""
    fun setName(name: String) {
        chosenName = name
    }

    private val _selectedEvent: MutableLiveData<EventEntity> = MutableLiveData()
    val selectedEvent: LiveData<EventEntity> get() = _selectedEvent
    fun setSelectedEvent(event: EventEntity) {
        _selectedEvent.value = event
    }

    private val eventListener = object : EventSelectListener {
        override fun onEventSelected(event: EventEntity) {
            setSelectedEvent(event)
        }
    }

    private val guestListener = object : GuestSelectListener {
        override fun onGuestSelect(selectedGuest: GuestEntity) {
            setSelectedGuest(selectedGuest)
        }
    }

    private val _selectedGuest: MutableLiveData<GuestEntity> = MutableLiveData()
    val selectedGuest: LiveData<GuestEntity> get() = _selectedGuest
    fun setSelectedGuest(guest: GuestEntity) {
        _selectedGuest.value = guest
    }

    fun goToEventPage(fragment: ChooseButtonFragment) {
        fragment.findNavController()
            .navigate(
                ChooseButtonFragmentDirections.actionChooseButtonFragmentToEventsFragment(
                    eventListener
                )
            )
    }

    fun goToGuestPage(fragment: ChooseButtonFragment) {
        fragment.findNavController()
            .navigate(
                ChooseButtonFragmentDirections.actionChooseButtonFragmentToGuestsFragment(
                    guestListener
                )
            )
    }

    companion object {
        @Volatile
        private var instance: ChooseButtonPresenter? = null

        fun getInstance(): ChooseButtonPresenter =
            instance ?: synchronized(this) {
                instance ?: ChooseButtonPresenter()
            }
    }
}