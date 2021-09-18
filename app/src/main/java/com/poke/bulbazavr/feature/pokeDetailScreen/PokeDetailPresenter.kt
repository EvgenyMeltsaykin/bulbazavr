package com.poke.bulbazavr.feature.pokeDetailScreen

import android.util.Log
import com.poke.api.useCase.GetPokemonUseCase
import com.poke.core.data.dto.PokemonDTO
import com.poke.database.usecases.AddFavoritePokemonUseCase
import com.poke.database.usecases.DeleteFavoritePokemonUseCase
import com.poke.database.usecases.GetFavoritePokemonUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class PokeDetailPresenter @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase,
    private val getFavoritePokemonUseCase: GetFavoritePokemonUseCase,
    private val addFavoritePokemonUseCase: AddFavoritePokemonUseCase,
    private val deleteFavoritePokemonUseCase: DeleteFavoritePokemonUseCase
) : MvpPresenter<PokeDetailView>() {

    private var pokemonName = ""
    private var isFavorite = false
    private lateinit var originalPokemonInfo: PokemonDTO
    private lateinit var visiblePokemonInfo: PokemonDTO
    private var disposable: CompositeDisposable = CompositeDisposable()
    fun init(pokemonName: String) {
        this.pokemonName = pokemonName
        loadInformation()
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }

    private fun loadInformation() {
        disposable.add(getPokemonInfo())
        disposable.add(getFavoritePokemonInfo())
    }

    private fun getFavoritePokemonInfo(): @NonNull Disposable {
        return getFavoritePokemonUseCase(pokemonName).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    isFavorite = true
                    viewState.isFavoritePokemon()
                },
                {
                    isFavorite = false
                    viewState.isNotFavoritePokemon()
                }
            )
    }

    private fun getPokemonInfo(): @NonNull Disposable {
        return getPokemonUseCase.invoke(pokemonName).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("POKEMON_INFO", "response = ${it}")
                    setOriginalPokemonInfo(it.toPokemonDTO())
                    visiblePokemonInfo = it.toPokemonDTO()
                    hideAllInfo()
                    viewState.setupInfoPokemon(visiblePokemonInfo)
                },
                {

                }
            )
    }

    private fun hideAllInfo() {
        visiblePokemonInfo.stats.info = null
        visiblePokemonInfo.abilities.info = null
    }

    private fun setOriginalPokemonInfo(pokemon: PokemonDTO) {
        originalPokemonInfo = pokemon
    }

    fun visibleActionStats() {
        if (visiblePokemonInfo.stats.visible) {
            visiblePokemonInfo.stats.info = null
        } else {
            visiblePokemonInfo.stats.info = originalPokemonInfo.stats.info
        }
        visiblePokemonInfo.stats.visible = !visiblePokemonInfo.stats.visible
        viewState.setupInfoPokemon(visiblePokemonInfo)
    }

    fun visibleActionAbilities() {
        if (visiblePokemonInfo.abilities.visible) {
            visiblePokemonInfo.abilities.info = null
        } else {
            visiblePokemonInfo.abilities.info = originalPokemonInfo.abilities.info
        }
        visiblePokemonInfo.abilities.visible = !visiblePokemonInfo.abilities.visible
        viewState.setupInfoPokemon(visiblePokemonInfo)
    }

    fun onFavoriteClick() {
        if (isFavorite) {
            deleteFavoritePokemonUseCase(pokemonName).subscribe()
            viewState.isNotFavoritePokemon()
        } else {
            addFavoritePokemonUseCase(originalPokemonInfo).subscribe()
            viewState.isFavoritePokemon()
        }
        isFavorite = !isFavorite
    }

}