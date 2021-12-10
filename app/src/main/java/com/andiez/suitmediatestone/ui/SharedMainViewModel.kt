package com.andiez.suitmediatestone.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andiez.suitmediatestone.model.local.EventEntity
import com.andiez.suitmediatestone.model.local.GuestEntity
import com.andiez.suitmediatestone.source.GuestApiStatus
import com.andiez.suitmediatestone.source.TestRepository
import com.andiez.suitmediatestone.utils.DataDummy
import kotlinx.coroutines.*
import java.lang.Exception

class SharedMainViewModel(private val repository: TestRepository = TestRepository()) : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _selectedEvent: MutableLiveData<EventEntity> = MutableLiveData()
    val selectedEvent: LiveData<EventEntity> get() = _selectedEvent
    fun setSelectedEvent(event: EventEntity) {
        _selectedEvent.value = event
    }

    private val _selectedGuest: MutableLiveData<GuestEntity> = MutableLiveData()
    val selectedGuest: LiveData<GuestEntity> get() = _selectedGuest
    fun setSelectedGuest(guest: GuestEntity) {
        _selectedGuest.value = guest
    }

    private val _state: MutableLiveData<GuestApiStatus> = MutableLiveData()
    val state: LiveData<GuestApiStatus> get() = _state
    fun setState(state: GuestApiStatus) {
        _state.value = state
    }

    val events: List<EventEntity> = DataDummy.getEventsDummy()

    fun getGuests(): LiveData<List<GuestEntity>> {
        val guests = MutableLiveData<List<GuestEntity>>()
        coroutineScope.launch {
            setState(GuestApiStatus.LOADING)
            try {
                guests.postValue(repository.getGuests().value)
                setState(GuestApiStatus.DONE)
            } catch (e: Exception) {
                e.printStackTrace()
                setState(GuestApiStatus.ERROR)
            }
        }
        return guests
    }

    fun clear() {
        _selectedEvent.value = null
        _selectedGuest.value = null
    }
}