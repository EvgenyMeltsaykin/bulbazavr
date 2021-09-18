package com.poke.bulbazavr.feature.pokeTamagochiScreen

import com.poke.database.usecases.AddFoodPokemonUseCase
import com.poke.database.usecases.AddFunPokemonUseCase
import com.poke.database.usecases.GetFavoritePokemonUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class TamagochiPresenter @Inject constructor(
    private val getFavoritePokemonUseCase: GetFavoritePokemonUseCase,
    private val addFunPokemonUseCase: AddFunPokemonUseCase,
    private val addFoodPokemonUseCase: AddFoodPokemonUseCase

) : MvpPresenter<TamagochiView>() {

    private var pokemonName: String = ""

    fun init(pokemonName: String) {
        this.pokemonName = pokemonName
        loadInformation(pokemonName)
    }

    private fun loadInformation(pokemonName: String) {
        getFavoritePokemonUseCase(pokemonName).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.setupInfo(it.toFavoritePokemonDTO())
                },
                { }
            )
    }

    fun onFeedClick() {
        addFoodPokemonUseCase(pokemonName).subscribe {
            loadInformation(pokemonName)
        }
    }

    fun onPlayClick() {
        addFunPokemonUseCase(pokemonName).subscribe { loadInformation(pokemonName) }
    }

    fun onFullInfoClick() {
        viewState.navigateToFullInfo(pokemonName)
    }

    fun updateInfo() {
        getFavoritePokemonUseCase(pokemonName).observeOn(AndroidSchedulers.mainThread())
            .subscribe({ },
                {
                    viewState.navigateToFavoriteList()
                }
            )
    }
}