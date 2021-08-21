package com.poke.bulbazavr.feature.pokeFavoritesScreen

import com.poke.core.data.dto.FavoritePokemonDTO
import com.poke.database.repositories.FavoritePokemonRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter
import javax.inject.Inject

class PokeFavoritesPresenter @Inject constructor(
    private val pokemonRepository: FavoritePokemonRepository
) : MvpPresenter<PokeFavoritesView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadInformation()
    }

    fun loadInformation() {
        pokemonRepository.getAllFavoritePokemon().observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { pokemons ->
                    viewState.showFavoritePokemons(pokemons.map { pokemonEntity -> pokemonEntity.toFavoritePokemonDTO() })
                },
                { }
            )
    }

    fun onPokemonClick(pokemon: FavoritePokemonDTO) {
        viewState.navigateToTamagochi(pokemon)
    }
}