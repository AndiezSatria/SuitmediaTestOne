package com.andiez.suitmediatestone.source

import com.andiez.suitmediatestone.model.remote.GuestResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface GuestService {
    @GET("596dec7f0f000023032b8017")
    fun getAllGuests(): Deferred<List<GuestResponse>>
}

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

object Network {
    private val retrofit =
        Retrofit.Builder()
            .baseUrl("http://www.mocky.io/v2/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    val guestsService = retrofit.create(GuestService::class.java)
}

enum class GuestApiStatus { LOADING, ERROR, DONE }