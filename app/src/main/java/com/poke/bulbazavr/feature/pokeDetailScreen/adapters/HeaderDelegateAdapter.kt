package com.poke.bulbazavr.feature.pokeDetailScreen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.poke.bulbazavr.R
import com.poke.bulbazavr.databinding.HeaderTextItemBinding
import com.poke.bulbazavr.utils.delegate.adapter.DelegateAdapter
import com.poke.bulbazavr.utils.delegate.adapter.DelegateAdapterItem
import com.poke.core.utils.Constants

data class HeaderDelegateItem(
    private val id: Int
) : DelegateAdapterItem {
    override fun id(): Int = id
    override fun content(): Int = id
}

class HeaderDelegateAdapter() :
    DelegateAdapter<HeaderDelegateItem, HeaderDelegateAdapter.HeaderViewHolder>(HeaderDelegateItem::class.java) {
    inner class HeaderViewHolder(private val binding: HeaderTextItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HeaderDelegateItem) {
            with(binding) {
                clMain.background = when (item.id()) {
                    Constants.BEGIN_HEADER_ID -> AppCompatResources.getDrawable(
                        itemView.context,
                        R.drawable.header_item_top_round_background
                    )
                    else -> AppCompatResources.getDrawable(
                        itemView.context,
                        R.drawable.header_item_bottom_round_background
                    )
                }
                executePendingBindings()
            }
        }
    }

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        HeaderViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.header_text_item,
                parent,
                false
            )
        )

    override fun bindViewHolder(
        item: HeaderDelegateItem,
        viewHolder: HeaderViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(item)
    }
}