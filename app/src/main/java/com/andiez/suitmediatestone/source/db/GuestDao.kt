package com.andiez.suitmediatestone.source.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andiez.suitmediatestone.model.local.GuestCacheEntity

@Dao
interface GuestDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg guestCacheEntity: GuestCacheEntity)

    @Query("SELECT * FROM table_guest")
    fun getGuests(): LiveData<List<GuestCacheEntity>>
}