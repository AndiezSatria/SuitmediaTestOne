package com.andiez.suitmediatestone.helper

import androidx.recyclerview.widget.DiffUtil
import com.andiez.suitmediatestone.model.local.GuestEntity

class GuestDiffCallback(
    private val oldGuestList: List<GuestEntity>,
    private val newGuestList: List<GuestEntity>
) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldGuestList.size
    }

    override fun getNewListSize(): Int {
        return newGuestList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldGuestList[oldItemPosition].id == newGuestList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = oldGuestList[oldItemPosition]
        val newEmployee = newGuestList[newItemPosition]
        return oldEmployee.name == newEmployee.name && oldEmployee.birthdate == newEmployee.birthdate && oldEmployee.image == newEmployee.image
    }
}