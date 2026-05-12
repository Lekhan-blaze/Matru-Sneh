package com.matrusineh.health.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.matrusineh.health.databinding.ItemWeeklyKickBinding

class WeeklyKickAdapter : ListAdapter<Pair<String, Float>, WeeklyKickAdapter.VH>(DIFF) {

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Pair<String, Float>>() {
            override fun areItemsTheSame(a: Pair<String, Float>, b: Pair<String, Float>) = a.first == b.first
            override fun areContentsTheSame(a: Pair<String, Float>, b: Pair<String, Float>) = a == b
        }
    }

    inner class VH(val binding: ItemWeeklyKickBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemWeeklyKickBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val (day, avg) = getItem(position)
        holder.binding.tvDay.text = day
        holder.binding.tvAvgKicks.text = if (avg == 0f) "—" else String.format("%.1f", avg)
    }
}
