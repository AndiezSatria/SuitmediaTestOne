package com.andiez.suitmediatestone.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.andiez.suitmediatestone.model.local.GuestEntity
import com.andiez.suitmediatestone.model.remote.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TestRepository(private val service: GuestService = Network.guestsService) {

    suspend fun getGuests(): LiveData<List<GuestEntity>> {
        val guests = MutableLiveData<List<GuestEntity>>()
        withContext(Dispatchers.IO) {
            val guestsResponse = service.getAllGuests().await()
            guests.postValue(guestsResponse.asDomainModel())
        }
        return guests
    }
}