package com.poke.bulbazavr.feature.pokeDetailScreen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.poke.bulbazavr.R
import com.poke.bulbazavr.databinding.HeaderInfoTextItemBinding
import com.poke.bulbazavr.utils.delegate.adapter.DelegateAdapter
import com.poke.bulbazavr.utils.delegate.adapter.DelegateAdapterItem
import java.util.*

data class HeaderInfoDelegateItem(
    private val text: String,
    private val id: Int
) : DelegateAdapterItem {
    override fun id(): Int = id
    override fun content(): String = text
}

class HeaderInfoDelegateAdapter(private val onClick: (id: Int) -> Unit) :
    DelegateAdapter<HeaderInfoDelegateItem, HeaderInfoDelegateAdapter.HeaderViewHolder>(
        HeaderInfoDelegateItem::class.java
    ) {
    private var clickTime: Long = 0

    inner class HeaderViewHolder(private val binding: HeaderInfoTextItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HeaderInfoDelegateItem, onClick: (id: Int) -> Unit) {
            with(binding) {
                tvTitle.text = item.content()
                animationAppearanceSmooth(itemView)
                root.setOnClickListener {
                    val nowTime = Calendar.getInstance().timeInMillis
                    if (nowTime - clickTime > 20) {
                        clickTime = nowTime
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
                R.layout.header_info_text_item,
                parent,
                false
            )
        )

    override fun bindViewHolder(
        item: HeaderInfoDelegateItem,
        viewHolder: HeaderViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(item, onClick)
    }
}