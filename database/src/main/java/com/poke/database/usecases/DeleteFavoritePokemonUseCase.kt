package com.poke.database.usecases

import com.poke.database.repositories.FavoritePokemonRepositoryDao
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

interface DeleteFavoritePokemonUseCase {
    operator fun invoke(pokemonName: String): Completable
}

class DeleteFavoritePokemonUseCaseImpl @Inject constructor(
    private val pokemonRepository: FavoritePokemonRepositoryDao
) : DeleteFavoritePokemonUseCase {

    override fun invoke(pokemonName: String): Completable =
        pokemonRepository.delete(pokemonName)

}