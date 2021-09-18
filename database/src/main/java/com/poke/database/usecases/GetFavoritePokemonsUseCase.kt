package com.poke.database.usecases

import com.poke.core.data.entity.PokemonEntity
import com.poke.database.repositories.FavoritePokemonRepositoryDao
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface GetFavoritePokemonsUseCase {
    operator fun invoke(): Single<List<PokemonEntity>>
}

class GetFavoritePokemonsUseCaseImpl @Inject constructor(
    private val pokemonRepository: FavoritePokemonRepositoryDao
) : GetFavoritePokemonsUseCase {

    override fun invoke(): Single<List<PokemonEntity>> = pokemonRepository.getAllFavoritePokemons()

}