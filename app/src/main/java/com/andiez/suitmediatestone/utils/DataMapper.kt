package com.andiez.suitmediatestone.utils

import com.andiez.suitmediatestone.model.local.GuestEntity
import com.andiez.suitmediatestone.model.remote.GuestResponse
import com.andiez.suitmediatestone.source.local.entity.GuestRealm

object DataMapper {
    fun mapResponsesToRealms(input: List<GuestResponse>): List<GuestRealm> {
        val guestRealms = ArrayList<GuestRealm>()
        input.map {
            val realm = GuestRealm(
                it.id,
                it.name,
                it.birthdate,
                "https://media-cdn.tripadvisor.com/media/photo-i/1a/9e/40/c0/alas-purwo-is-one-of.jpg"
            )
            guestRealms.add(realm)
        }
        return guestRealms
    }

    fun mapRealmsToDomain(input: List<GuestRealm>): List<GuestEntity> {
        val guests = ArrayList<GuestEntity>()
        input.map {
            val guest = GuestEntity(
                it.id,
                it.name,
                it.birthdate,
                it.image
            )
            guests.add(guest)
        }
        return guests
    }
}