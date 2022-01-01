package com.andiez.suitmediatestone.di

import com.andiez.suitmediatestone.domain.repository.ITestRepository
import com.andiez.suitmediatestone.domain.usecase.TestInteractor
import com.andiez.suitmediatestone.domain.usecase.TestUseCase
import com.andiez.suitmediatestone.helper.EventSelectListener
import com.andiez.suitmediatestone.helper.GuestSelectListener
import com.andiez.suitmediatestone.source.TestNewRepository
import com.andiez.suitmediatestone.source.local.LocalDataSource
import com.andiez.suitmediatestone.source.local.realm.DatabaseHelper
import com.andiez.suitmediatestone.source.remote.Network
import com.andiez.suitmediatestone.source.remote.RemoteDataSource
import com.andiez.suitmediatestone.ui.event.EventPresenter
import com.andiez.suitmediatestone.ui.guest.GuestPresenter
import com.andiez.suitmediatestone.ui.main.ChooseButtonPresenter
import com.andiez.suitmediatestone.ui.main.HomePresenter
import com.andiez.suitmediatestone.utils.AppExecutors
import com.onesignal.OneSignal
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
        val oneSignalDevice = OneSignal.getDeviceState()

        return TestNewRepository.getInstance(
            remoteDataSource,
            localDataSource,
            appExecutors,
            oneSignalDevice
        )
    }

    fun provideUseCase(): TestUseCase {
        val repository = provideRepository()
        return TestInteractor(repository)
    }

    fun provideHomePresenter(): HomePresenter = HomePresenter.getInstance()

    fun provideChooseButtonPresenter(): ChooseButtonPresenter = ChooseButtonPresenter.getInstance(
        provideUseCase()
    )

    fun provideEventPresenter(listener: EventSelectListener): EventPresenter =
        EventPresenter.getInstance(listener)

    fun provideGuestPresenter(listener: GuestSelectListener): GuestPresenter =
        GuestPresenter.getInstance(
            provideUseCase(), listener
        )
}