package com.poke.database.usecases

import com.poke.core.data.dto.PokemonDTO
import com.poke.database.repositories.FavoritePokemonRepositoryDao
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

interface AddFavoritePokemonUseCase {
    operator fun invoke(pokemon: PokemonDTO): Completable
}

class AddFavoritePokemonUseCaseImpl @Inject constructor(
    private val pokemonRepository: FavoritePokemonRepositoryDao
) : AddFavoritePokemonUseCase {

    override fun invoke(pokemon: PokemonDTO): Completable =
        pokemonRepository.insert(pokemon)

}