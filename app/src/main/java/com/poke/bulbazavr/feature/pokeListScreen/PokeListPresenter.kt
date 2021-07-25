package com.poke.bulbazavr.feature.pokeListScreen

import com.poke.bulbazavr.api.PokeApiService
import com.poke.bulbazavr.data.Pokemon
import com.poke.bulbazavr.di.AppComponent
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class PokeListPresenter: MvpPresenter<PokeListView>() {

    init {
        viewState.setPokemons(listOf(
            Pokemon("test1"),
            Pokemon("test2"),
            Pokemon("test3"),
            Pokemon("test4"),
            //Pokemon(pokeApiService.getString()),
        ))
    }

    fun onPokemonClick(){
        viewState.navigateToDetailPokemon()
    }
}