package com.andiez.suitmediatestone

import android.app.Application
import com.andiez.suitmediatestone.di.Injection
import com.onesignal.OneSignal
import io.realm.Realm

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Realm.setDefaultConfiguration(Injection.provideRealmConfig())
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

    companion object {
        private const val ONESIGNAL_APP_ID = "APP_ID"
    }
}