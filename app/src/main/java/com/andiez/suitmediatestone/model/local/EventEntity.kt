package com.andiez.suitmediatestone.model.local

data class EventEntity(
    var id: Int = 0,
    var name: String = "",
    var imageUrl: String = "",
    var description: String = "",
    var date: String = "",
    var tags: ArrayList<String> = ArrayList(),
    var lat: Long = 0,
    var lon: Long = 0,
)