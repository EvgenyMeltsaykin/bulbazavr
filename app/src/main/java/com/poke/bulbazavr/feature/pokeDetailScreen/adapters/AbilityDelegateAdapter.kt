package com.poke.bulbazavr.feature.pokeDetailScreen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.poke.bulbazavr.R
import com.poke.bulbazavr.databinding.AbilityItemBinding
import com.poke.bulbazavr.utils.delegate.adapter.DelegateAdapter
import com.poke.bulbazavr.utils.delegate.adapter.DelegateAdapterItem
import com.poke.core.data.dto.AbilityDTO
import com.poke.core.utils.Constants.ABILITY_ID

data class AbilityDelegateItem(
    private val ability: AbilityDTO,
) : DelegateAdapterItem {
    override fun id(): Int = ABILITY_ID
    override fun content(): AbilityDTO = ability
}

class AbilityDelegateAdapter :
    DelegateAdapter<AbilityDelegateItem, AbilityDelegateAdapter.AbilityViewHolder>(
        AbilityDelegateItem::class.java
    ) {
    inner class AbilityViewHolder(private var binding: AbilityItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AbilityDelegateItem) {
            with(binding) {
                tvAbilityName.text = item.content().name
                tvDescriptionEffect.text =
                    itemView.context.getString(R.string.effect, item.content().effectInfo?.effect)
                tvDescriptionShortEffect.text = itemView.context.getString(
                    R.string.short_effect,
                    item.content().effectInfo?.shortEffect
                )
                animationAppearanceSmooth(itemView)
                executePendingBindings()
            }
        }
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        AbilityViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.ability_item,
                parent,
                false
            )
        )

    override fun bindViewHolder(
        item: AbilityDelegateItem,
        viewHolder: AbilityViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(item)
    }
}