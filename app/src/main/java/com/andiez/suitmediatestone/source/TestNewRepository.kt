package com.andiez.suitmediatestone.source

import android.util.Log
import com.andiez.suitmediatestone.domain.repository.ITestRepository
import com.andiez.suitmediatestone.model.local.EventEntity
import com.andiez.suitmediatestone.model.local.GuestEntity
import com.andiez.suitmediatestone.model.remote.GuestResponse
import com.andiez.suitmediatestone.source.local.LocalDataSource
import com.andiez.suitmediatestone.source.remote.ApiResponse
import com.andiez.suitmediatestone.source.remote.RemoteDataSource
import com.andiez.suitmediatestone.utils.AppExecutors
import com.andiez.suitmediatestone.utils.DataMapper
import com.onesignal.OSDeviceState
import com.onesignal.OneSignal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.json.JSONObject

class TestNewRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
    private val oneSignalDevice: OSDeviceState?
) : ITestRepository {
    override fun getAllGuest(): Flow<Resource<List<GuestEntity>>> =
        object : NetworkBoundResource<List<GuestEntity>, List<GuestResponse>>() {
            override fun loadFromDB(): Flow<List<GuestEntity>> {
                return localDataSource.getAllGuest().map { DataMapper.mapRealmsToDomain(it) }
            }

            override fun shouldFetch(data: List<GuestEntity>?): Boolean =
//                data == null || data.isEmpty()
                true

            override suspend fun createCall(): Flow<ApiResponse<List<GuestResponse>>> =
                remoteDataSource.getAllGuest()

            override suspend fun saveCallResult(data: List<GuestResponse>) {
                val guestList = DataMapper.mapResponsesToRealms(data)
                localDataSource.insertGuests(guestList)
            }

        }.asFlow()

    override fun sendNotification(
        guestEntity: GuestEntity?,
        eventEntity: EventEntity?
    ): Flow<Resource<String>> = flow {
        try {
            var message = ""
            emit(Resource.Loading("Menunggu."))
            OneSignal.postNotification(
                JSONObject(
                    "{'contents': { " +
                            "'en': 'Anda ${
                                when {
                                    guestEntity != null && eventEntity != null -> "memilih " + guestEntity.name + " dan " + eventEntity.name
                                    guestEntity != null -> "memilih " + guestEntity.name + " dan tidak memilih event."
                                    eventEntity != null -> "memilih " + eventEntity.name + " dan tidak memilih guest."
                                    else -> "tidak memilih apapun."
                                }
                            }'}," +
                            "'include_player_ids': ['" + oneSignalDevice?.userId + "']}"
                ), object : OneSignal.PostNotificationResponseHandler {
                    override fun onSuccess(response: JSONObject?) {
                        message += "Notifikasi berhasil dikirim."
                        Log.i("Repository", response.toString())
                    }

                    override fun onFailure(response: JSONObject?) {
                        message += "Notifikasi gagal dikirim."
                        Log.e("Repository", response.toString())
                    }
                }
            )
            emit(Resource.Success(message))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: TestNewRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors,
            oneSignalDevice: OSDeviceState?
        ): TestNewRepository =
            instance ?: synchronized(this) {
                instance ?: TestNewRepository(remoteData, localData, appExecutors, oneSignalDevice)
            }
    }
}