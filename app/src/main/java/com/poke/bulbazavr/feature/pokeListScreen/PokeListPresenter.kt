package com.poke.bulbazavr.feature.pokeListScreen

import android.util.Log
import com.poke.bulbazavr.api.data.responses.BaseResponse
import com.poke.bulbazavr.api.useCase.GetNextPagePokemonsUseCase
import com.poke.bulbazavr.api.useCase.GetPokemonsUseCase
import com.poke.bulbazavr.data.Pokemon
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class PokeListPresenter @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase,
    private val getNextPagePokemonsUseCase: GetNextPagePokemonsUseCase
) : MvpPresenter<PokeListView>() {

    private val pokemons: MutableList<Pokemon> = mutableListOf()
    private var nextPageUrl: String? = ""
    private var isLoading = false

    init {
        getFirstPokemons()
    }

    fun loadNextPage() {
        if (isLoading || nextPageUrl == null) return
        isLoading = true
        getNextPagePokemons()
    }

    private fun getFirstPokemons() {
        getPokemonsUseCase.invoke(
            params = Unit,
            onSuccess = { response ->
                onSuccessLoadPokemons(response)
            },
            onFailed = {
                isLoading = false
                Log.d("TAG", "Throwable $it")
            }
        )
    }

    private fun getNextPagePokemons() {
        getNextPagePokemonsUseCase.invoke(
            params = nextPageUrl!!,
            onSuccess = { response ->
                onSuccessLoadPokemons(response)
            },
            onFailed = {
                isLoading = false
                Log.d("TAG", "Throwable $it")
            }
        )
    }

    private fun onSuccessLoadPokemons(response: BaseResponse<Pokemon>) {
        isLoading = false
        nextPageUrl = response.next
        pokemons.addAll(response.results)
        viewState.setPokemons(pokemons)
    }

    fun onPokemonClick() {
        viewState.navigateToDetailPokemon()
    }
}