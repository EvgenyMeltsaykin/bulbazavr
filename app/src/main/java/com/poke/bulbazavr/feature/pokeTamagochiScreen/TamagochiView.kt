package com.poke.bulbazavr.feature.pokeTamagochiScreen

import com.poke.bulbazavr.data.FavoritePokemonDTO
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

interface TamagochiView : MvpView {
    @StateStrategyType(AddToEndStrategy::class)
    fun setupInfo(favoritePokemon: FavoritePokemonDTO)
}