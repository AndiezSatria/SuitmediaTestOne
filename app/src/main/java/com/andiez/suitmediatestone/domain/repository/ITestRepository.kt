package com.andiez.suitmediatestone.domain.repository

import com.andiez.suitmediatestone.model.local.EventEntity
import com.andiez.suitmediatestone.model.local.GuestEntity
import com.andiez.suitmediatestone.source.Resource
import kotlinx.coroutines.flow.Flow

interface ITestRepository {
    fun getAllGuest(): Flow<Resource<List<GuestEntity>>>
   fun sendNotification(guestEntity: GuestEntity?, eventEntity: EventEntity?): Flow<Resource<String>>
}