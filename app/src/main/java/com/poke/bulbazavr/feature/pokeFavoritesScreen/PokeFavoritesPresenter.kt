package com.poke.bulbazavr.feature.pokeFavoritesScreen

import com.poke.core.data.dto.FavoritePokemonDTO
import com.poke.database.usecases.GetFavoritePokemonsUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import javax.inject.Inject

class PokeFavoritesPresenter @Inject constructor(
    private val getFavoritePokemonsUseCase: GetFavoritePokemonsUseCase
) : MvpPresenter<PokeFavoritesView>() {
    private var disposable: CompositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadInformation()
    }

    fun loadInformation() {
        disposable.add(getFavoritePokemons())
    }

    private fun getFavoritePokemons(): @NonNull Disposable {
        return getFavoritePokemonsUseCase().observeOn(AndroidSchedulers.mainThread())
            .subscribe { pokemons ->
                viewState.showFavoritePokemons(pokemons.map { pokemonEntity -> pokemonEntity.toFavoritePokemonDTO() })
            }
    }

    fun onPokemonClick(pokemon: FavoritePokemonDTO) {
        viewState.navigateToTamagochi(pokemon)
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}