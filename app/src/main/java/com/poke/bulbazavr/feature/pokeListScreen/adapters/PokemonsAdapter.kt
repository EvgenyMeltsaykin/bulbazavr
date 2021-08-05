package com.poke.bulbazavr.feature.pokeListScreen.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.poke.bulbazavr.R
import com.poke.bulbazavr.data.PokemonDTO
import com.poke.bulbazavr.databinding.PokemonListItemBinding
import com.poke.bulbazavr.utils.EqualsDiffCallback


class PokemonsAdapter(
    val onPokemonClick: (pokemon: PokemonDTO, rvItemBinding: PokemonListItemBinding) -> Unit
) : ListAdapter<PokemonDTO, PokemonsAdapter.PokemonViewHolder>(EqualsDiffCallback { a, b -> a == b }) {

    class PokemonViewHolder(private val binding: PokemonListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            pokemon: PokemonDTO,
            onPokemonClick: (pokemon: PokemonDTO, rvItemBinding: PokemonListItemBinding) -> Unit
        ) {
            with(binding) {
                Glide.with(itemView).load(pokemon.sprites.frontDefault).into(ivPokemonAvatar)
                tvName.text = pokemon.name.replaceFirstChar { it.uppercaseChar() }
                ivPokemonAvatar.transitionName = pokemon.name
                root.setOnClickListener {
                    onPokemonClick.invoke(pokemon, binding)
                }
                setAnimation(itemView)
            }
        }

        private fun setAnimation(viewToAnimate: View) {
            val animation: Animation =
                AnimationUtils.loadAnimation(viewToAnimate.context, R.anim.smooth_animation)
            viewToAnimate.startAnimation(animation)
        }

    }

    override fun submitList(list: List<PokemonDTO>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder =
        PokemonViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater
                    .from(parent.context), R.layout.pokemon_list_item, parent, false
            )

        )

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(getItem(position),onPokemonClick)
    }
}