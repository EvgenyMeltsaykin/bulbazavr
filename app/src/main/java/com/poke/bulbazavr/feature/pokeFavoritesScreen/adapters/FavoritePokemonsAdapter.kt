package com.poke.bulbazavr.feature.pokeFavoritesScreen.adapters

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
import com.poke.bulbazavr.databinding.PokemonListItemBinding
import com.poke.core.data.dto.FavoritePokemonDTO
import com.poke.core.utils.EqualsDiffCallback

class FavoritePokemonsAdapter(
    private val onPokemonClick: (pokemon: FavoritePokemonDTO) -> Unit
) : ListAdapter<FavoritePokemonDTO, FavoritePokemonsAdapter.FavoritePokemonViewHolder>(
    EqualsDiffCallback { a, b -> a == b }) {

    class FavoritePokemonViewHolder(private val binding: PokemonListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            pokemon: FavoritePokemonDTO,
            onPokemonClick: (pokemon: FavoritePokemonDTO) -> Unit
        ) {
            with(binding) {
                Glide.with(itemView).load(pokemon.url).into(ivPokemonAvatar)
                tvName.text = pokemon.name.replaceFirstChar { it.uppercaseChar() }
                ivPokemonAvatar.transitionName = pokemon.name
                root.setOnClickListener {
                    onPokemonClick.invoke(pokemon)
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

    override fun submitList(list: List<FavoritePokemonDTO>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritePokemonViewHolder =
        FavoritePokemonViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater
                    .from(parent.context), R.layout.pokemon_list_item, parent, false
            )

        )

    override fun onBindViewHolder(holder: FavoritePokemonViewHolder, position: Int) {
        holder.bind(getItem(position), onPokemonClick)
    }
}