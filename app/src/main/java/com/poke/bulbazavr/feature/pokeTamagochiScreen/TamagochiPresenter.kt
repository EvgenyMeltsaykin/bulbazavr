package com.poke.bulbazavr.feature.pokeTamagochiScreen

import com.poke.bulbazavr.database.repositories.FavoritePokemonRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class TamagochiPresenter @Inject constructor(
    private val pokemonRepository: FavoritePokemonRepository
) : MvpPresenter<TamagochiView>() {

    private var pokemonName: String = ""

    fun init(pokemonName: String) {
        this.pokemonName = pokemonName
        loadInformation(pokemonName)
    }

    private fun loadInformation(pokemonName: String) {
        pokemonRepository.getPokemon(pokemonName).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.setupInfo(it.toFavoritePokemonDTO())
                },
                { }
            )
    }

    fun onFeedClick() {
        pokemonRepository.plusFoodIndicator(pokemonName = pokemonName).subscribe {
            loadInformation(pokemonName)
        }
    }

    fun onPlayClick() {
        pokemonRepository.plusFunIndicator(pokemonName).subscribe {
            loadInformation(pokemonName)
        }
    }

    fun onFullInfoClick() {
        viewState.navigateToFullInfo(pokemonName)
    }
}