package com.andiez.suitmediatestone.source.local

import com.andiez.suitmediatestone.source.local.entity.GuestRealm
import com.andiez.suitmediatestone.source.local.realm.IDatabaseHelper
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val databaseHelper: IDatabaseHelper) {
    suspend fun getAllGuest(): Flow<List<GuestRealm>> = databaseHelper.getGuests()
    suspend fun insertGuests(guests: List<GuestRealm>) = databaseHelper.insertGuests(guests)

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(databaseHelper: IDatabaseHelper): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(databaseHelper)
            }
    }
}