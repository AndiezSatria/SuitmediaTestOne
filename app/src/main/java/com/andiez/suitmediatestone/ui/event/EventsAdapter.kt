package com.andiez.suitmediatestone.ui.event

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andiez.suitmediatestone.databinding.LayoutListEventBinding
import com.andiez.suitmediatestone.helper.EventDiffCallback
import com.andiez.suitmediatestone.model.local.EventEntity

class EventsAdapter internal constructor(
    private val handler: EventItemHandler
) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {
    private val listEvent = ArrayList<EventEntity>()
    fun setListNotes(listEvent: List<EventEntity>) {
        val diffCallback = EventDiffCallback(this.listEvent, listEvent)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listEvent.clear()
        this.listEvent.addAll(listEvent)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventsAdapter.EventViewHolder {
        val binding = LayoutListEventBinding.inflate(LayoutInflater.from(parent.context))
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventsAdapter.EventViewHolder, position: Int) {
        holder.bind(listEvent[position])
    }

    override fun getItemCount(): Int {
        return listEvent.size
    }

    inner class EventViewHolder(private val binding: LayoutListEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EventEntity) {
            val adapter = TagAdapter()
            binding.item = item
            binding.root.setOnClickListener { handler.onItemClick(bindingAdapterPosition, item) }
            binding.rvTags.layoutManager =
                LinearLayoutManager(binding.rvTags.context, LinearLayoutManager.HORIZONTAL, false)
            adapter.submitList(item.tags)
            binding.rvTags.adapter = adapter
        }
    }

    interface EventItemHandler {
        fun onItemClick(position: Int, item: EventEntity)
    }
}