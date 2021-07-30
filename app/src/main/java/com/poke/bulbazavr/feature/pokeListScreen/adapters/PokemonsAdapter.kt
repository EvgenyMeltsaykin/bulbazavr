package com.poke.bulbazavr.feature.pokeListScreen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.poke.bulbazavr.R
import com.poke.bulbazavr.data.PokemonDTO
import com.poke.bulbazavr.databinding.PokemonListItemBinding
import com.poke.bulbazavr.utils.EqualsDiffCallback

class PokemonsAdapter(
    val onPokemonClick: (pokemon: PokemonDTO) -> Unit
) : ListAdapter<PokemonDTO, PokemonsAdapter.PokemonViewHolder>(EqualsDiffCallback { a, b -> a == b }) {

    class PokemonViewHolder(private val binding: PokemonListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: PokemonDTO,
            onPokemonClick: (pokemon: PokemonDTO) -> Unit
        ) {
            with(binding) {
                Glide.with(itemView).load(item.sprites.frontDefault).into(ivPokemonAvatar)
                tvName.text = item.name.replaceFirstChar { it.uppercaseChar() }
                root.setOnClickListener {
                    onPokemonClick.invoke(item)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder = PokemonViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater
                .from(parent.context), R.layout.pokemon_list_item, parent, false
        )

    )

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(getItem(position),onPokemonClick)
    }
}