package com.poke.bulbazavr.feature.pokeListScreen

import android.util.Log
import com.poke.bulbazavr.api.data.request.OffsetLimitRequest
import com.poke.bulbazavr.api.data.responses.BaseResponse
import com.poke.bulbazavr.api.useCase.GetPokemonsUseCase
import com.poke.bulbazavr.data.Pokemon
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class PokeListPresenter @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase
) : MvpPresenter<PokeListView>() {

    private val pokemons: MutableList<Pokemon> = mutableListOf()
    private var page: Int = 0
    private var nextPageUrl: String? = ""
    private var isLoading = false

    init {
        loadNextPage()
    }

    fun loadNextPage() {
        if (isLoading || nextPageUrl == null) return
        isLoading = true
        getNextPagePokemons()
    }

    private fun getNextPagePokemons() {
        getPokemonsUseCase.invoke(
            params = OffsetLimitRequest(page = page),
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
        page++
        pokemons.addAll(response.results!!)
        viewState.setPokemons(pokemons)
    }

    fun onPokemonClick() {
        viewState.navigateToDetailPokemon()
    }
}