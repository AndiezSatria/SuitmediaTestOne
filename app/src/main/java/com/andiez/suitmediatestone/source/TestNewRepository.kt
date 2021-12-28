package com.andiez.suitmediatestone.source

import com.andiez.suitmediatestone.domain.repository.ITestRepository
import com.andiez.suitmediatestone.model.local.GuestEntity
import com.andiez.suitmediatestone.model.remote.GuestResponse
import com.andiez.suitmediatestone.source.local.LocalDataSource
import com.andiez.suitmediatestone.source.remote.ApiResponse
import com.andiez.suitmediatestone.source.remote.RemoteDataSource
import com.andiez.suitmediatestone.utils.AppExecutors
import com.andiez.suitmediatestone.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TestNewRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ITestRepository {
    override fun getAllGuest(): Flow<Resource<List<GuestEntity>>> =
        object : NetworkBoundResource<List<GuestEntity>, List<GuestResponse>>() {
            override suspend fun loadFromDB(): Flow<List<GuestEntity>> {
                return localDataSource.getAllGuest().map { DataMapper.mapRealmsToDomain(it) }
            }

            override fun shouldFetch(data: List<GuestEntity>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GuestResponse>>> =
                remoteDataSource.getAllGuest()

            override suspend fun saveCallResult(data: List<GuestResponse>) {
                val guestList = DataMapper.mapResponsesToRealms(data)
                localDataSource.insertGuests(guestList)
            }

        }.asFlow()

    companion object {
        @Volatile
        private var instance: TestNewRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): TestNewRepository =
            instance ?: synchronized(this) {
                instance ?: TestNewRepository(remoteData, localData, appExecutors)
            }
    }
}