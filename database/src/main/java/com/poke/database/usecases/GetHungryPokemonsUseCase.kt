package com.poke.database.usecases

import com.poke.core.data.entity.PokemonEntity
import com.poke.database.repositories.FavoritePokemonRepositoryDao
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface GetHungryPokemonsUseCase {
    operator fun invoke(): Single<List<PokemonEntity>>
}

class GetHungryPokemonsUseCaseImpl @Inject constructor(
    private val pokemonRepository: FavoritePokemonRepositoryDao
) : GetHungryPokemonsUseCase {

    override fun invoke(): Single<List<PokemonEntity>> = pokemonRepository.getHungryPokemons()

}