package com.andiez.suitmediatestone.domain.usecase

import com.andiez.suitmediatestone.model.local.GuestEntity
import com.andiez.suitmediatestone.source.Resource
import kotlinx.coroutines.flow.Flow

interface TestUseCase {
    fun getAllGuest(): Flow<Resource<List<GuestEntity>>>
}