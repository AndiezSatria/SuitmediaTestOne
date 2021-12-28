package com.andiez.suitmediatestone

import android.app.Application
import com.andiez.suitmediatestone.di.Injection
import io.realm.Realm

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Realm.setDefaultConfiguration(Injection.provideRealmConfig())
    }
}