package com.poke.bulbazavr.feature.pokeDetailScreen

import com.poke.core.data.dto.PokemonDTO
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

interface PokeDetailView : MvpView {
    @StateStrategyType(AddToEndStrategy::class)
    fun setupInfoPokemon(pokemon: PokemonDTO)

    @StateStrategyType(AddToEndStrategy::class)
    fun isFavoritePokemon()

    @StateStrategyType(AddToEndStrategy::class)
    fun isNotFavoritePokemon()
}