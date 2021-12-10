package com.andiez.suitmediatestone.source.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andiez.suitmediatestone.model.local.GuestCacheEntity

@Database(entities = [GuestCacheEntity::class], version = 1, exportSchema = false)
abstract class GuestDatabase : RoomDatabase() {
    abstract fun guestDao(): GuestDao

    companion object{
        @Volatile
        private var INSTANCE: GuestDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): GuestDatabase {
            if (INSTANCE == null) {
                synchronized(GuestDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        GuestDatabase::class.java,
                        "note_database"
                    ).build()
                }
            }
            return INSTANCE as GuestDatabase
        }
    }
}