package com.poke.database.usecases

import com.poke.database.repositories.FavoritePokemonControlRepositoryDao
import io.reactivex.rxjava3.core.Completable
import java.util.*
import javax.inject.Inject

interface AddFunPokemonUseCase {
    operator fun invoke(pokemonName: String): Completable
}

class AddFunPokemonUseCaseImpl @Inject constructor(
    private val pokemonRepository: FavoritePokemonControlRepositoryDao
) : AddFunPokemonUseCase {

    override fun invoke(pokemonName: String): Completable {
        val currentTime = Calendar.getInstance().time.time
        return pokemonRepository.plusFunIndicator(pokemonName, currentTime)
    }

}