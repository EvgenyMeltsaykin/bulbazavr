package com.poke.database.usecases

import com.poke.core.data.entity.PokemonEntity
import com.poke.database.repositories.FavoritePokemonControlRepositoryDao
import com.poke.database.repositories.FavoritePokemonRepositoryDao
import io.reactivex.rxjava3.core.Single
import java.util.*
import javax.inject.Inject

interface GetFavoritePokemonUseCase {
    operator fun invoke(pokemonName: String): Single<PokemonEntity>
}

class GetFavoritePokemonUseCaseImpl @Inject constructor(
    private val pokemonRepository: FavoritePokemonRepositoryDao,
    private val pokemonControlRepository: FavoritePokemonControlRepositoryDao
) : GetFavoritePokemonUseCase {

    companion object {
        private const val FIVE_MINUTE_MILLIS = 5 * 60 * 1000
    }

    override fun invoke(pokemonName: String): Single<PokemonEntity> {
        val pokemon = pokemonRepository.getPokemon(pokemonName)
            ?: return Single.error(NullPointerException())
        val currentTime = Calendar.getInstance().time.time
        val timePassedLastFeeding = currentTime - pokemon.lastTimeFeeding
        val timePassedLastGaming = currentTime - pokemon.lastTimeGaming
        var minusFood = (timePassedLastFeeding / FIVE_MINUTE_MILLIS).toInt()
        var minusFun = (timePassedLastGaming / FIVE_MINUTE_MILLIS).toInt()
        if (minusFood > pokemon.foodIndicator) minusFood = pokemon.foodIndicator
        if (minusFun > pokemon.funIndicator) minusFun = pokemon.funIndicator
        pokemonControlRepository.minusFoodIndicator(
            pokemonName = pokemonName,
            countFood = minusFood
        ).subscribe()
        pokemonControlRepository.minusFunIndicator(pokemonName = pokemonName, countFun = minusFun)
            .subscribe()
        val newPokemon = pokemon.copy(
            funIndicator = pokemon.funIndicator - minusFun,
            foodIndicator = pokemon.foodIndicator - minusFood,
            lastTimeFeeding = if (minusFood > 0) currentTime else pokemon.lastTimeFeeding,
            lastTimeGaming = if (minusFun > 0) currentTime else pokemon.lastTimeGaming
        )
        pokemonRepository.update(newPokemon.toFavoritePokemonDTO()).subscribe()
        return Single.just(newPokemon)
    }
}