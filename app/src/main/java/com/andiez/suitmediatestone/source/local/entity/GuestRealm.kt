package com.andiez.suitmediatestone.source.local.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class GuestRealm(
    @PrimaryKey
    var id: Int = 0,
    var name: String = "",
    var birthdate: String = "",
    var image: String = ""
): RealmObject()