package com.andiez.suitmediatestone.model.local

data class EventEntity(
    var id: Int = 0,
    var name: String = "",
    var imageUrl: String = "",
    var description: String = "",
    var date: String = "",
    var tags: ArrayList<String> = ArrayList(),
    var lat: Double = 0.0,
    var lon: Double = 0.0,
)