package com.andiez.suitmediatestone.utils

import com.andiez.suitmediatestone.model.local.EventEntity
import kotlin.collections.ArrayList

object DataDummy {
    fun getEventsDummy(): List<EventEntity> {
        val listEvent = ArrayList<EventEntity>()
        val tags = ArrayList<String>()
        tags.add("#Bussiness")
        tags.add("#Marathon")
        tags.add("#Triathon")
        listEvent.add(
            EventEntity(
                1,
                "Event ABC",
                "https://media-cdn.tripadvisor.com/media/photo-s/09/5a/84/aa/amazon-forest-day-tours.jpg",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam tempor pulvinar suscipit. Morbi a blandit ante. Sed in elit varius odio sodales maximus non quis eros. Sed urna nibh, consectetur vel neque congue, pharetra gravida augue. Proin a pretium ipsum. Proin pharetra efficitur tempus. Ut dignissim porta vestibulum. Sed eu pretium augue. Praesent volutpat enim sit amet quam auctor, nec efficitur massa congue. Pellentesque semper interdum ultricies. Aenean enim urna, cursus a urna eget, vestibulum varius lorem. Aliquam sit amet neque interdum ante consequat eleifend. Sed ut quam eros. Vestibulum ultrices semper tellus vitae hendrerit. Curabitur dapibus ligula odio, nec venenatis mauris pharetra a.",
                "20-10-2021",
                tags,

            )
        )
        listEvent.add(
            EventEntity(
                2,
                "Event Running",
                "https://media-cdn.tripadvisor.com/media/photo-s/09/5a/84/aa/amazon-forest-day-tours.jpg",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam tempor pulvinar suscipit. Morbi a blandit ante. Sed in elit varius odio sodales maximus non quis eros. Sed urna nibh, consectetur vel neque congue, pharetra gravida augue. Proin a pretium ipsum. Proin pharetra efficitur tempus. Ut dignissim porta vestibulum. Sed eu pretium augue. Praesent volutpat enim sit amet quam auctor, nec efficitur massa congue. Pellentesque semper interdum ultricies. Aenean enim urna, cursus a urna eget, vestibulum varius lorem. Aliquam sit amet neque interdum ante consequat eleifend. Sed ut quam eros. Vestibulum ultrices semper tellus vitae hendrerit. Curabitur dapibus ligula odio, nec venenatis mauris pharetra a.",
                "12-05-2020",
                tags,
            )
        )
        listEvent.add(
            EventEntity(
                3,
                "Event Marathon",
                "https://media-cdn.tripadvisor.com/media/photo-s/09/5a/84/aa/amazon-forest-day-tours.jpg",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam tempor pulvinar suscipit. Morbi a blandit ante. Sed in elit varius odio sodales maximus non quis eros. Sed urna nibh, consectetur vel neque congue, pharetra gravida augue. Proin a pretium ipsum. Proin pharetra efficitur tempus. Ut dignissim porta vestibulum. Sed eu pretium augue. Praesent volutpat enim sit amet quam auctor, nec efficitur massa congue. Pellentesque semper interdum ultricies. Aenean enim urna, cursus a urna eget, vestibulum varius lorem. Aliquam sit amet neque interdum ante consequat eleifend. Sed ut quam eros. Vestibulum ultrices semper tellus vitae hendrerit. Curabitur dapibus ligula odio, nec venenatis mauris pharetra a.",
                "01-08-2019",
                tags,
            )
        )
        listEvent.add(
            EventEntity(
                4,
                "Event Relay Run",
                "https://media-cdn.tripadvisor.com/media/photo-s/09/5a/84/aa/amazon-forest-day-tours.jpg",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam tempor pulvinar suscipit. Morbi a blandit ante. Sed in elit varius odio sodales maximus non quis eros. Sed urna nibh, consectetur vel neque congue, pharetra gravida augue. Proin a pretium ipsum. Proin pharetra efficitur tempus. Ut dignissim porta vestibulum. Sed eu pretium augue. Praesent volutpat enim sit amet quam auctor, nec efficitur massa congue. Pellentesque semper interdum ultricies. Aenean enim urna, cursus a urna eget, vestibulum varius lorem. Aliquam sit amet neque interdum ante consequat eleifend. Sed ut quam eros. Vestibulum ultrices semper tellus vitae hendrerit. Curabitur dapibus ligula odio, nec venenatis mauris pharetra a.",
                "21-02-2022",
                tags,
            )
        )
        listEvent.add(
            EventEntity(
                5,
                "Event Pole Jump",
                "https://media-cdn.tripadvisor.com/media/photo-s/09/5a/84/aa/amazon-forest-day-tours.jpg",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam tempor pulvinar suscipit. Morbi a blandit ante. Sed in elit varius odio sodales maximus non quis eros. Sed urna nibh, consectetur vel neque congue, pharetra gravida augue. Proin a pretium ipsum. Proin pharetra efficitur tempus. Ut dignissim porta vestibulum. Sed eu pretium augue. Praesent volutpat enim sit amet quam auctor, nec efficitur massa congue. Pellentesque semper interdum ultricies. Aenean enim urna, cursus a urna eget, vestibulum varius lorem. Aliquam sit amet neque interdum ante consequat eleifend. Sed ut quam eros. Vestibulum ultrices semper tellus vitae hendrerit. Curabitur dapibus ligula odio, nec venenatis mauris pharetra a.",
                "15-11-2021",
                tags,
            )
        )
        listEvent.add(
            EventEntity(
                6,
                "Event Swimming",
                "https://media-cdn.tripadvisor.com/media/photo-s/09/5a/84/aa/amazon-forest-day-tours.jpg",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam tempor pulvinar suscipit. Morbi a blandit ante. Sed in elit varius odio sodales maximus non quis eros. Sed urna nibh, consectetur vel neque congue, pharetra gravida augue. Proin a pretium ipsum. Proin pharetra efficitur tempus. Ut dignissim porta vestibulum. Sed eu pretium augue. Praesent volutpat enim sit amet quam auctor, nec efficitur massa congue. Pellentesque semper interdum ultricies. Aenean enim urna, cursus a urna eget, vestibulum varius lorem. Aliquam sit amet neque interdum ante consequat eleifend. Sed ut quam eros. Vestibulum ultrices semper tellus vitae hendrerit. Curabitur dapibus ligula odio, nec venenatis mauris pharetra a.",
                "02-09-2021",
                tags,
            )
        )
        return listEvent
    }
}