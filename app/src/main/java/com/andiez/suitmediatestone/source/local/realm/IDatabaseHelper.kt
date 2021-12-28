package com.andiez.suitmediatestone.source.local.realm

import com.andiez.suitmediatestone.source.local.entity.GuestRealm
import kotlinx.coroutines.flow.Flow

interface IDatabaseHelper {
    fun getGuests(): Flow<List<GuestRealm>>
    suspend fun insertGuests(guests: List<GuestRealm>)
}