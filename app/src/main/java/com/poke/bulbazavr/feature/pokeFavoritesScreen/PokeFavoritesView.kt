package com.poke.bulbazavr.feature.pokeFavoritesScreen

import com.poke.core.data.dto.FavoritePokemonDTO
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface PokeFavoritesView : MvpView {
    @StateStrategyType(AddToEndStrategy::class)
    fun showFavoritePokemons(pokemons: List<FavoritePokemonDTO>)

    @StateStrategyType(SkipStrategy::class)
    fun navigateToTamagochi(pokemon: FavoritePokemonDTO)
}