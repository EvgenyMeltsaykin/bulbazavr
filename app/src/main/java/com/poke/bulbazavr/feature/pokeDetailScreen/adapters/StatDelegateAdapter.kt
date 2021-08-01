package com.poke.bulbazavr.feature.pokeDetailScreen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.poke.bulbazavr.R
import com.poke.bulbazavr.data.StatDTO
import com.poke.bulbazavr.databinding.StatItemBinding
import com.poke.bulbazavr.utils.Constans.STAT_ID
import com.poke.bulbazavr.utils.delegate.adapter.DelegateAdapter
import com.poke.bulbazavr.utils.delegate.adapter.DelegateAdapterItem

data class StatDelegateItem(
    private val stat: StatDTO,
) : DelegateAdapterItem {
    override fun id(): Int = STAT_ID
    override fun content(): StatDTO = stat
}

class StatDelegateAdapter :
    DelegateAdapter<StatDelegateItem, StatDelegateAdapter.StatViewHolder>(StatDelegateItem::class.java) {
    inner class StatViewHolder(private var binding: StatItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StatDelegateItem) {
            with(binding) {
                tvStatName.text = item.content().statName
                tvBaseStat.text = item.content().baseStat.toString()
                tvEffort.text = item.content().effort.toString()
                animationAppearanceSmooth(itemView)
                executePendingBindings()
            }
        }
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        StatViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.stat_item,
                parent,
                false
            )
        )

    override fun bindViewHolder(
        item: StatDelegateItem,
        viewHolder: StatViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(item)
    }
}