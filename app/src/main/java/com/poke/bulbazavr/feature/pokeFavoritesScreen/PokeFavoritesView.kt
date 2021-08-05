package com.poke.bulbazavr.feature.pokeFavoritesScreen

import com.poke.bulbazavr.data.FavoritePokemonDTO
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

interface PokeFavoritesView : MvpView {
    @StateStrategyType(AddToEndStrategy::class)
    fun showFavoritePokemons(pokemons: List<FavoritePokemonDTO>)
}