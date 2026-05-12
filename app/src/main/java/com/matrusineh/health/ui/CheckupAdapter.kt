package com.matrusineh.health.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.matrusineh.health.R
import com.matrusineh.health.data.db.Checkup
import com.matrusineh.health.databinding.ItemCheckupBinding
import com.matrusineh.health.utils.DateUtils
import java.util.concurrent.TimeUnit

class CheckupAdapter(private val onDelete: (Checkup) -> Unit) :
    ListAdapter<Checkup, CheckupAdapter.VH>(DIFF) {

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Checkup>() {
            override fun areItemsTheSame(a: Checkup, b: Checkup) = a.id == b.id
            override fun areContentsTheSame(a: Checkup, b: Checkup) = a == b
        }
    }

    inner class VH(val binding: ItemCheckupBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemCheckupBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val checkup = getItem(position)
        holder.binding.tvCheckupType.text = checkup.type
        holder.binding.tvCheckupDate.text = DateUtils.formatDate(checkup.dateEpoch)

        val daysLeft = TimeUnit.MILLISECONDS.toDays(checkup.dateEpoch - System.currentTimeMillis()).coerceAtLeast(0)
        holder.binding.tvDaysLeft.text = "${daysLeft}d"

        val ctx = holder.binding.root.context
        val (indicatorColor, textColor) = when {
            daysLeft <= 2 -> Pair(R.color.error, R.color.error)
            daysLeft <= 7 -> Pair(R.color.warning, R.color.yellow_countdown)
            else -> Pair(R.color.secondary, R.color.secondary)
        }
        holder.binding.urgencyIndicator.setBackgroundColor(ContextCompat.getColor(ctx, indicatorColor))
        holder.binding.tvDaysLeft.setTextColor(ContextCompat.getColor(ctx, textColor))

        holder.binding.btnDeleteCheckup.setOnClickListener { onDelete(checkup) }
    }
}
