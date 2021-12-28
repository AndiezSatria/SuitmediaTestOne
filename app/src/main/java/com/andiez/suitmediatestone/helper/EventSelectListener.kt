package com.andiez.suitmediatestone.helper

import com.andiez.suitmediatestone.model.local.EventEntity
import java.io.Serializable

interface EventSelectListener: Serializable {
    fun onEventSelected(event: EventEntity)
}