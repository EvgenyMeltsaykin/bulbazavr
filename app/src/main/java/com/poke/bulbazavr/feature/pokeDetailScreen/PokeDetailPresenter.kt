package com.poke.bulbazavr.feature.pokeDetailScreen

import android.util.Log
import com.poke.bulbazavr.api.useCase.GetPokemonUseCase
import com.poke.bulbazavr.database.repositories.FavoritePokemonRepository
import com.poke.core.data.dto.PokemonDTO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class PokeDetailPresenter @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase,
    private val pokemonRepository: FavoritePokemonRepository
) : MvpPresenter<PokeDetailView>() {

    private var pokemonName = ""
    private var isFavorite = false
    private lateinit var originalPokemonInfo: PokemonDTO
    private lateinit var visiblePokemonInfo: PokemonDTO
    fun init(pokemonName: String) {
        this.pokemonName = pokemonName
        loadInformation()
    }

    private fun loadInformation() {
        getPokemonUseCase.invoke(pokemonName).observeOn(AndroidSchedulers.mainThread())
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

        pokemonRepository.getPokemon(pokemonName).observeOn(AndroidSchedulers.mainThread())
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
            pokemonRepository.delete(pokemonName).subscribe()
            viewState.isNotFavoritePokemon()
        } else {
            pokemonRepository.insert(originalPokemonInfo).subscribe()
            viewState.isFavoritePokemon()
        }
        isFavorite = !isFavorite
    }

}