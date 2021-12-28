package com.andiez.suitmediatestone.helper

import com.andiez.suitmediatestone.model.local.GuestEntity
import java.io.Serializable

interface GuestSelectListener : Serializable {
    fun onGuestSelect(selectedGuest: GuestEntity)
}