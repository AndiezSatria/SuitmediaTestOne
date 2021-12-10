package com.andiez.suitmediatestone.model.remote

import com.andiez.suitmediatestone.model.local.GuestEntity
import com.squareup.moshi.Json

data class GuestResponse(
    @field:Json(name = "id") var id: Int,
    @field:Json(name = "name") var name: String,
    @field:Json(name = "birthdate") var birthdate: String,
)
fun List<GuestResponse>.asDomainModel(): List<GuestEntity> {
    return this.map {
        GuestEntity(
            it.id,
            it.name,
            it.birthdate,
            "https://media-cdn.tripadvisor.com/media/photo-i/1a/9e/40/c0/alas-purwo-is-one-of.jpg",
        )
    }
}