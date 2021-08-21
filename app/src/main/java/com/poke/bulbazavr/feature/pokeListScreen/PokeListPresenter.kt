package com.poke.bulbazavr.feature.pokeListScreen

import com.poke.api.useCase.GetPokemonsUseCase
import com.poke.bulbazavr.databinding.PokemonListItemBinding
import com.poke.core.data.api.request.OffsetLimitRequest
import com.poke.core.data.api.responses.BaseResponse
import com.poke.core.data.api.responses.PokemonResponse
import com.poke.core.data.dto.PokemonDTO
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
        if (nextPageUrl!!.isNotEmpty()) viewState.showRecyclerViewLoader()
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
                    viewState.hideRecyclerViewLoader()
                    isLoading = false
                },
                { throwable ->
                    viewState.hideLoader()
                    viewState.hideRecyclerViewLoader()
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

    fun onPokemonClick(pokemon: PokemonDTO, rvItemBinding: PokemonListItemBinding) {
        viewState.navigateToDetailPokemon(pokemon.name, rvItemBinding)
    }
}