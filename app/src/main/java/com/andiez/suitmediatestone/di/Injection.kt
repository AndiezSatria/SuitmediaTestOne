package com.andiez.suitmediatestone.di

import com.andiez.suitmediatestone.domain.repository.ITestRepository
import com.andiez.suitmediatestone.domain.usecase.TestInteractor
import com.andiez.suitmediatestone.domain.usecase.TestUseCase
import com.andiez.suitmediatestone.source.TestNewRepository
import com.andiez.suitmediatestone.source.local.LocalDataSource
import com.andiez.suitmediatestone.source.local.realm.DatabaseHelper
import com.andiez.suitmediatestone.source.remote.GuestService
import com.andiez.suitmediatestone.source.remote.Network
import com.andiez.suitmediatestone.source.remote.RemoteDataSource
import com.andiez.suitmediatestone.ui.main.HomePresenter
import com.andiez.suitmediatestone.utils.AppExecutors
import io.realm.RealmConfiguration

object Injection {
    private const val realmVersion = 1L
    fun provideRealmConfig(): RealmConfiguration =
        RealmConfiguration.Builder()
            .schemaVersion(realmVersion)
            .build()

    fun provideRepository(): ITestRepository {
        val remoteDataSource = RemoteDataSource.getInstance(Network.guestsService)
        val localDataSource = LocalDataSource.getInstance(DatabaseHelper.getInstance())
        val appExecutors = AppExecutors()

        return TestNewRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideUseCase(): TestUseCase {
        val repository = provideRepository()
        return TestInteractor(repository)
    }

    fun provideHomePresenter(): HomePresenter = HomePresenter.getInstance()
}