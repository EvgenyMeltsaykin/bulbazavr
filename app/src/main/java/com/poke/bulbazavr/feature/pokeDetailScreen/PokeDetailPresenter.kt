package com.poke.bulbazavr.feature.pokeDetailScreen

import com.poke.bulbazavr.api.useCase.GetPokemonUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class PokeDetailPresenter @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase
) : MvpPresenter<PokeDetailView>() {

    private var pokemonName = ""

    fun init(pokemonName: String) {
        this.pokemonName = pokemonName
        loadInformation()
    }

    private fun loadInformation() {
        getPokemonUseCase.invoke(pokemonName).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.setupInfoPokemon(it.toPokemonDTO())
                },
                {

                }
            )

    }
}