package com.andiez.suitmediatestone.ui.main

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.andiez.suitmediatestone.domain.usecase.TestUseCase
import com.andiez.suitmediatestone.helper.EventSelectListener
import com.andiez.suitmediatestone.helper.GuestSelectListener
import com.andiez.suitmediatestone.model.local.EventEntity
import com.andiez.suitmediatestone.model.local.GuestEntity
import com.andiez.suitmediatestone.source.Resource
import com.andiez.suitmediatestone.ui.base.BasePresenter
import com.google.android.material.snackbar.Snackbar

class ChooseButtonPresenter private constructor(private val useCase: TestUseCase) :
    BasePresenter() {
    var chosenName: String = ""
    fun setName(name: String) {
        chosenName = name
    }

    private val _selectedEvent: MutableLiveData<EventEntity> = MutableLiveData(null)
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

    private val _selectedGuest: MutableLiveData<GuestEntity> = MutableLiveData(null)
    val selectedGuest: LiveData<GuestEntity> get() = _selectedGuest
    fun setSelectedGuest(guest: GuestEntity) {
        _selectedGuest.value = guest
    }

    fun sendNotification(): LiveData<Resource<String>> =
        Transformations.switchMap(_selectedEvent) { event ->
            Transformations.switchMap(_selectedGuest) { guest ->
                useCase.sendNotification(guest, event).asLiveData()
            }
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

        fun getInstance(useCase: TestUseCase): ChooseButtonPresenter =
            instance ?: synchronized(this) {
                instance ?: ChooseButtonPresenter(useCase)
            }
    }
}