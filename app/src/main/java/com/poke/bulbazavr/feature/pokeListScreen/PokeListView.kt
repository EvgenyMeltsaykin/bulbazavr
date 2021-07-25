package com.poke.bulbazavr.feature.pokeListScreen

import com.poke.bulbazavr.data.Pokemon
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface PokeListView: MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun navigateToDetailPokemon()

    @StateStrategyType(AddToEndStrategy::class)
    fun setPokemons(pokemons: List<Pokemon>)
}