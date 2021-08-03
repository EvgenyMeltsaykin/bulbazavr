package com.poke.bulbazavr.feature.pokeDetailScreen.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.poke.bulbazavr.R
import com.poke.bulbazavr.databinding.HeaderTextItemBinding
import com.poke.bulbazavr.utils.Constans.END_HEADER_ID
import com.poke.bulbazavr.utils.Constans.STAT_ID
import com.poke.bulbazavr.utils.delegate.adapter.DelegateAdapter
import com.poke.bulbazavr.utils.delegate.adapter.DelegateAdapterItem
import java.util.*

data class HeaderDelegateItem(
    private val text: String,
    private val id: Int
) : DelegateAdapterItem {
    override fun id(): Int = id
    override fun content(): String = text
}

class HeaderDelegateAdapter(private val onClick: (id: Int) -> Unit) :
    DelegateAdapter<HeaderDelegateItem, HeaderDelegateAdapter.HeaderViewHolder>(HeaderDelegateItem::class.java) {
    private var clickTime: Long = 0
    inner class HeaderViewHolder(private val binding: HeaderTextItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HeaderDelegateItem, onClick: (id: Int) -> Unit) {
            with(binding) {
                clMain.background = when (item.id()) {
                    STAT_ID -> AppCompatResources.getDrawable(
                        itemView.context,
                        R.drawable.header_item_top_round_background
                    )
                    END_HEADER_ID -> AppCompatResources.getDrawable(
                        itemView.context,
                        R.drawable.header_item_bottom_round_background
                    )
                    else -> AppCompatResources.getDrawable(
                        itemView.context,
                        R.drawable.header_item_background
                    )
                }
                tvTitle.text = item.content()
                animationAppearanceSmooth(itemView)
                root.setOnClickListener {
                    val nowTime = Calendar.getInstance().timeInMillis
                    if (nowTime - clickTime > 20) {
                        clickTime = nowTime
                        Log.d("pokemon info", "click")
                        onClick.invoke(item.id())
                    }

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
        viewHolder.bind(item, onClick)
    }
}