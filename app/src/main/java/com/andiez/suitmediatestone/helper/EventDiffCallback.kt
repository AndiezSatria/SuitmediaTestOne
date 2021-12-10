package com.andiez.suitmediatestone.helper

import androidx.recyclerview.widget.DiffUtil
import com.andiez.suitmediatestone.model.local.EventEntity

class EventDiffCallback(
    private val oldEventList: List<EventEntity>,
    private val newEventList: List<EventEntity>
) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldEventList.size
    }

    override fun getNewListSize(): Int {
        return newEventList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldEventList[oldItemPosition].id == newEventList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = oldEventList[oldItemPosition]
        val newEmployee = newEventList[newItemPosition]
        return oldEmployee.name == newEmployee.name && oldEmployee.date == newEmployee.date && oldEmployee.imageUrl == newEmployee.imageUrl
    }
}