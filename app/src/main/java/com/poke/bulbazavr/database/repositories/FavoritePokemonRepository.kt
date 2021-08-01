package com.poke.bulbazavr.database.repositories

import com.poke.bulbazavr.data.PokemonDTO
import com.poke.bulbazavr.database.dao.FavoritePokemonDao
import com.poke.bulbazavr.database.data.PokemonEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

interface FavoritePokemonRepositoryDao {
    fun getAllFavoritePokemon(): Single<List<PokemonEntity>>
    fun insert(pokemon: PokemonDTO): Completable
}

class FavoritePokemonRepository(private val pokemonDao: FavoritePokemonDao) :
    FavoritePokemonRepositoryDao {
    override fun getAllFavoritePokemon(): Single<List<PokemonEntity>> =
        pokemonDao.loadAllFavoritePokemon().subscribeOn(Schedulers.io())

    override fun insert(pokemon: PokemonDTO): Completable {
        return pokemonDao.addPokemon(
            pokemonEntity = PokemonEntity(
                name = pokemon.name,
                url = pokemon.sprites.backDefault ?: ""
            )
        )
    }
}