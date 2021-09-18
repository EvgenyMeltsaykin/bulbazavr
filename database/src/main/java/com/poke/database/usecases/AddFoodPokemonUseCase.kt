package com.poke.database.usecases

import com.poke.database.repositories.FavoritePokemonControlRepositoryDao
import io.reactivex.rxjava3.core.Completable
import java.util.*
import javax.inject.Inject

interface AddFoodPokemonUseCase {
    operator fun invoke(pokemonName: String): Completable
}

class AddFoodPokemonUseCaseImpl @Inject constructor(
    private val pokemonRepository: FavoritePokemonControlRepositoryDao
) : AddFoodPokemonUseCase {

    override fun invoke(pokemonName: String): Completable {
        val currentTime = Calendar.getInstance().time.time
        return pokemonRepository.plusFoodIndicator(pokemonName, currentTime)
    }
}