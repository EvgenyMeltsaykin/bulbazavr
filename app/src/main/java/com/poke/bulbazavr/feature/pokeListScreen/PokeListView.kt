package com.poke.bulbazavr.feature.pokeListScreen

import com.poke.bulbazavr.data.PokemonDTO
import com.poke.bulbazavr.databinding.PokemonListItemBinding
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface PokeListView: MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun navigateToDetailPokemon(name: String, rvItemBinding: PokemonListItemBinding)

    @StateStrategyType(AddToEndStrategy::class)
    fun setPokemons(pokemons: List<PokemonDTO>)

    @StateStrategyType(SkipStrategy::class)
    fun showLoader()

    @StateStrategyType(SkipStrategy::class)
    fun hideLoader()

    @StateStrategyType(SkipStrategy::class)
    fun showRecyclerViewLoader()

    @StateStrategyType(SkipStrategy::class)
    fun hideRecyclerViewLoader()

}