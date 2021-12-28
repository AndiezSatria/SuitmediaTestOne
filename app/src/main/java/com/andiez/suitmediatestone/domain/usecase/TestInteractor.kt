package com.andiez.suitmediatestone.domain.usecase

import com.andiez.suitmediatestone.domain.repository.ITestRepository
import com.andiez.suitmediatestone.model.local.GuestEntity
import com.andiez.suitmediatestone.source.Resource
import kotlinx.coroutines.flow.Flow

class TestInteractor(private val repository: ITestRepository) : TestUseCase {
    override fun getAllGuest(): Flow<Resource<List<GuestEntity>>> = repository.getAllGuest()
}