package com.poke.bulbazavr.feature.pokeListScreen

import android.util.Log
import com.poke.bulbazavr.api.data.request.OffsetLimitRequest
import com.poke.bulbazavr.api.data.responses.BaseResponse
import com.poke.bulbazavr.api.data.responses.PokemonResponse
import com.poke.bulbazavr.api.useCase.GetPokemonsUseCase
import com.poke.bulbazavr.data.PokemonDTO
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class PokeListPresenter @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase
) : MvpPresenter<PokeListView>() {

    private val pokemons: MutableList<PokemonDTO> = mutableListOf()
    private var page: Int = 0
    private var nextPageUrl: String? = ""
    private var isLoading = false

    init {
        loadNextPage()
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadNextPage()
    }

    fun loadNextPage() {
        if (isLoading || nextPageUrl == null) return
        isLoading = true
        getNextPagePokemons()
    }

    private fun getNextPagePokemons() {
        Log.d("POKEMON_LIST", "getNextPagePokemons")
        getPokemonsUseCase.invoke(
            params = OffsetLimitRequest(page = page),
            onSuccess = { response ->
                Log.d("POKEMON_LIST", "response = $response")
                onSuccessLoadPokemons(response)
            },
            onFailed = {
                isLoading = false
                Log.d("POKEMON_LIST", "Throwable $it")
            }
        )
    }

    private fun onSuccessLoadPokemons(response: BaseResponse<PokemonResponse>) {
        isLoading = false
        nextPageUrl = response.next
        page++
        pokemons.addAll(response.results.map { it.toPokemonDTO() })
        viewState.setPokemons(pokemons)
    }

    fun onPokemonClick(pokemon: PokemonDTO) {
        viewState.navigateToDetailPokemon(pokemon.name)
    }
}