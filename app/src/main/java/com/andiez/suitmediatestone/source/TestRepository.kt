package com.andiez.suitmediatestone.source

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.andiez.suitmediatestone.model.local.GuestEntity
import com.andiez.suitmediatestone.model.local.asDomainModel
import com.andiez.suitmediatestone.model.remote.asDatabaseModel
import com.andiez.suitmediatestone.source.db.GuestDao
import com.andiez.suitmediatestone.source.db.GuestDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TestRepository(
    private val service: GuestService = Network.guestsService,
    application: Application
) {
    private val guestDao: GuestDao

    init {
        val db = GuestDatabase.getDatabase(application)
        guestDao = db.guestDao()
    }

    fun getGuests(): LiveData<List<GuestEntity>> =
        Transformations.map(guestDao.getGuests()) {
            it.asDomainModel()
        }

    suspend fun fetchGuests() {
        withContext(Dispatchers.IO) {
            val guestsResponse = service.getAllGuests().await()
            guestDao.insert(*guestsResponse.asDatabaseModel())
            Log.i("Tes", guestsResponse.asDatabaseModel()[0].toString())
        }
    }
}