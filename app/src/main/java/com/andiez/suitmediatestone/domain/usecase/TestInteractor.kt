package com.andiez.suitmediatestone.domain.usecase

import com.andiez.suitmediatestone.domain.repository.ITestRepository
import com.andiez.suitmediatestone.model.local.EventEntity
import com.andiez.suitmediatestone.model.local.GuestEntity
import com.andiez.suitmediatestone.source.Resource
import kotlinx.coroutines.flow.Flow

class TestInteractor(private val repository: ITestRepository) : TestUseCase {
    override fun getAllGuest(): Flow<Resource<List<GuestEntity>>> = repository.getAllGuest()
    override fun sendNotification(
        guestEntity: GuestEntity?,
        eventEntity: EventEntity?
    ): Flow<Resource<String>> = repository.sendNotification(guestEntity, eventEntity)
}