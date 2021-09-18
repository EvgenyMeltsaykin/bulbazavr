package com.poke.database.usecases

import com.poke.core.data.dto.FavoritePokemonDTO
import com.poke.database.repositories.FavoritePokemonRepositoryDao
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

interface UpdateFavoritePokemonUseCase {
    operator fun invoke(favoritePokemonDTO: FavoritePokemonDTO): Completable
}

class UpdateFavoritePokemonUseCaseImpl @Inject constructor(
    private val pokemonRepository: FavoritePokemonRepositoryDao
) : UpdateFavoritePokemonUseCase {

    override fun invoke(favoritePokemonDTO: FavoritePokemonDTO): Completable =
        pokemonRepository.update(favoritePokemonDTO)

}