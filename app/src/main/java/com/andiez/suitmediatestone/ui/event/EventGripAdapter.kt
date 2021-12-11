package com.andiez.suitmediatestone.ui.event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andiez.suitmediatestone.databinding.LayoutGridEventBinding
import com.andiez.suitmediatestone.helper.EventDiffCallback
import com.andiez.suitmediatestone.model.local.EventEntity

class EventGripAdapter internal constructor(private val handler: GridHandler): RecyclerView.Adapter<EventGripAdapter.EventGripViewHolder>(){
    private val listEvent = ArrayList<EventEntity>()
    fun setListNotes(listEvent: List<EventEntity>) {
        val diffCallback = EventDiffCallback(this.listEvent, listEvent)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listEvent.clear()
        this.listEvent.addAll(listEvent)
        diffResult.dispatchUpdatesTo(this)
    }
    fun getItemAt(position: Int): EventEntity = listEvent[position]

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventGripViewHolder {
        val binding = LayoutGridEventBinding.inflate(LayoutInflater.from(parent.context))
        return EventGripViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventGripViewHolder, position: Int) {
        holder.bind(listEvent[position])
    }

    override fun getItemCount(): Int {
        return listEvent.size
    }

    inner class EventGripViewHolder(private val binding: LayoutGridEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EventEntity) {
            binding.item = item
            binding.root.setOnClickListener { handler.onItemClick(bindingAdapterPosition, item) }
        }
    }

    interface GridHandler {
        fun onItemClick(position: Int, item: EventEntity)
    }
}