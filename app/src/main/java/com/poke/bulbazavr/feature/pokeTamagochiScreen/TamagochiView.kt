package com.poke.bulbazavr.feature.pokeTamagochiScreen

import com.poke.core.data.dto.FavoritePokemonDTO
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface TamagochiView : MvpView {
    @StateStrategyType(AddToEndStrategy::class)
    fun setupInfo(favoritePokemon: FavoritePokemonDTO)

    @StateStrategyType(SkipStrategy::class)
    fun navigateToFullInfo(pokemonName: String)

    @StateStrategyType(SkipStrategy::class)
    fun navigateToFavoriteList()
}