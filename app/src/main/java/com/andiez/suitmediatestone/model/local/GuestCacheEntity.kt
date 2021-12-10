package com.andiez.suitmediatestone.model.local

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_guest")
data class GuestCacheEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "birthdate")
    var birthdate: String = "",

    @ColumnInfo(name = "image")
    var image: String = ""
)

fun List<GuestCacheEntity>.asDomainModel(): List<GuestEntity> {
    return this.map {
        GuestEntity(
            it.id,
            it.name,
            it.birthdate,
            it.image
        )
    }
}