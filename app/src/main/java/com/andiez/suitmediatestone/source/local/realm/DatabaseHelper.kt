package com.andiez.suitmediatestone.source.local.realm

import android.util.Log
import com.andiez.suitmediatestone.di.Injection
import com.andiez.suitmediatestone.source.local.entity.GuestRealm
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.executeTransactionAwait
import io.realm.kotlin.toFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow

class DatabaseHelper : IDatabaseHelper {
    private val config: RealmConfiguration = Injection.provideRealmConfig()
    override fun getGuests(): Flow<List<GuestRealm>> {
        val realm = Realm.getInstance(config)
        return realm.where(GuestRealm::class.java).findAllAsync().toFlow()
    }

    override suspend fun insertGuests(guests: List<GuestRealm>) {
        val realm = Realm.getInstance(config)
        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            realmTransaction.insertOrUpdate(guests)
        }
    }

    companion object {
        @Volatile
        private var instance: DatabaseHelper? = null

        fun getInstance(): DatabaseHelper =
            instance ?: synchronized(this) {
                instance ?: DatabaseHelper()
            }
    }
}