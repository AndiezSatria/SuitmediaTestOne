package com.andiez.suitmediatestone.utils

import com.andiez.suitmediatestone.model.local.EventEntity
import kotlin.collections.ArrayList

object DataDummy {
    fun getEventsDummy(): List<EventEntity> {
        val listEvent = ArrayList<EventEntity>()
        listEvent.add(
            EventEntity(
                1,
                "Event ABC",
                "https://media-cdn.tripadvisor.com/media/photo-s/09/5a/84/aa/amazon-forest-day-tours.jpg",
                "20-10-2021"
            )
        )
        listEvent.add(
            EventEntity(
                2,
                "Event Running",
                "https://media-cdn.tripadvisor.com/media/photo-s/09/5a/84/aa/amazon-forest-day-tours.jpg",
                "12-05-2020"
            )
        )
        listEvent.add(
            EventEntity(
                3,
                "Event Marathon",
                "https://media-cdn.tripadvisor.com/media/photo-s/09/5a/84/aa/amazon-forest-day-tours.jpg",
                "01-08-2019"
            )
        )
        listEvent.add(
            EventEntity(
                4,
                "Event Relay Run",
                "https://media-cdn.tripadvisor.com/media/photo-s/09/5a/84/aa/amazon-forest-day-tours.jpg",
                "21-02-2022"
            )
        )
        listEvent.add(
            EventEntity(
                5,
                "Event Pole Jump",
                "https://media-cdn.tripadvisor.com/media/photo-s/09/5a/84/aa/amazon-forest-day-tours.jpg",
                "15-11-2021"
            )
        )
        listEvent.add(
            EventEntity(
                6,
                "Event Swimming",
                "https://media-cdn.tripadvisor.com/media/photo-s/09/5a/84/aa/amazon-forest-day-tours.jpg",
                "02-09-2021"
            )
        )
        return listEvent
    }
}