package com.andiez.suitmediatestone.helper

import com.andiez.suitmediatestone.model.local.EventEntity
import java.io.Serializable

interface EventChooseListener: Serializable {
    fun onEventSelected(event: EventEntity)
}