package com.andiez.suitmediatestone.ui.guest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andiez.suitmediatestone.databinding.LayoutGridGuestBinding
import com.andiez.suitmediatestone.helper.GuestDiffCallback
import com.andiez.suitmediatestone.model.local.GuestEntity

class GuestAdapter internal constructor(
    private val itemHandler: GuestItemHandler
) : RecyclerView.Adapter<GuestAdapter.GuestViewHolder>() {
    private val listGuest = ArrayList<GuestEntity>()
    fun setListNotes(listGuest: List<GuestEntity>?) {
        if (listGuest == null) return
        val diffCallback = GuestDiffCallback(this.listGuest, listGuest)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listGuest.clear()
        this.listGuest.addAll(listGuest)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val binding = LayoutGridGuestBinding.inflate(LayoutInflater.from(parent.context))
        return GuestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(listGuest[position])
    }

    override fun getItemCount(): Int {
        return listGuest.size
    }

    inner class GuestViewHolder(private val binding: LayoutGridGuestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GuestEntity) {
            binding.item = item
            binding.root.setOnClickListener { itemHandler.onClick(item, bindingAdapterPosition) }
        }

    }

    interface GuestItemHandler {
        fun onClick(item: GuestEntity, position: Int)
    }

}