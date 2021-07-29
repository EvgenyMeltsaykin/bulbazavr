package com.poke.bulbazavr.feature.pokeListScreen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.poke.bulbazavr.R
import com.poke.bulbazavr.data.Pokemon
import com.poke.bulbazavr.databinding.PokemonListItemBinding
import com.poke.bulbazavr.utils.EqualsDiffCallback

class PokemonsAdapter(
    val onPokemonClick: (pokemon: Pokemon)->Unit
): ListAdapter<Pokemon, PokemonsAdapter.PokemonViewHolder>(EqualsDiffCallback { a, b -> a == b }){

    class PokemonViewHolder(private val binding:PokemonListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item:Pokemon,
                onPokemonClick: (pokemon: Pokemon) -> Unit){
            with(binding){
                Glide.with(itemView).load(item.urlPhoto).into(ivPokemonAvatar)
                tvName.text = item.name
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