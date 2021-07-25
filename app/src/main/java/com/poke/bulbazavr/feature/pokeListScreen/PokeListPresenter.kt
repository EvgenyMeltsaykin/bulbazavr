package com.poke.bulbazavr.feature.pokeListScreen

import android.util.Log
import com.poke.bulbazavr.api.PokeApiService
import com.poke.bulbazavr.data.Pokemon
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class PokeListPresenter @Inject constructor(
    private val pokeApiService: PokeApiService
) : MvpPresenter<PokeListView>() {

    private val pokemons: MutableList<Pokemon> = mutableListOf()
    private var nextPageUrl: String? = ""
    private var isLoading = false

    init {
        loadNextPage()
    }

    fun loadNextPage() {
        if (isLoading || nextPageUrl == null) return
        isLoading = true
        val getPokemons =
            if (nextPageUrl!!.isEmpty()) pokeApiService.getPokemons()
            else pokeApiService.getPokemons(nextPageUrl!!)

        getPokemons.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    isLoading = false
                    nextPageUrl = response.next
                    pokemons.addAll(response.results)
                    viewState.setPokemons(pokemons)
                }, {
                    isLoading = false
                    Log.d("TAG", "Throwable $it")
                })
    }

    fun onPokemonClick() {
        viewState.navigateToDetailPokemon()
    }
}