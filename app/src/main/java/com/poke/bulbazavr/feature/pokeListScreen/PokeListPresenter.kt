package com.poke.bulbazavr.feature.pokeListScreen

import com.poke.bulbazavr.api.data.request.OffsetLimitRequest
import com.poke.bulbazavr.api.data.responses.BaseResponse
import com.poke.bulbazavr.api.data.responses.PokemonResponse
import com.poke.bulbazavr.api.useCase.GetPokemonsUseCase
import com.poke.bulbazavr.data.PokemonDTO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
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
        if (nextPageUrl != null && nextPageUrl!!.isEmpty()) viewState.showLoader()
        getPokemonsUseCase.invoke(
            params = OffsetLimitRequest(page = page),
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    onSuccessLoadPokemons(response)
                    isLoading = false
                },
                { throwable ->
                    viewState.hideLoader()
                    isLoading = false
                }
            )
    }

    private fun onSuccessLoadPokemons(response: BaseResponse<PokemonResponse>) {
        nextPageUrl = response.next
        page++
        pokemons.addAll(response.results.map { it.toPokemonDTO() })
        viewState.setPokemons(pokemons)
        viewState.hideLoader()
    }

    fun onPokemonClick(pokemon: PokemonDTO) {
        viewState.navigateToDetailPokemon(pokemon.name)
    }
}