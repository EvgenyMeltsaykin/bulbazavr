package com.poke.database.repositories

import com.poke.core.data.dto.FavoritePokemonDTO
import com.poke.core.data.dto.PokemonDTO
import com.poke.core.data.entity.PokemonEntity
import com.poke.database.dao.FavoritePokemonDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

interface FavoritePokemonRepositoryDao {
    fun getAllFavoritePokemons(): Single<List<PokemonEntity>>
    fun insert(pokemon: PokemonDTO): Completable
    fun getPokemon(pokemonName: String): PokemonEntity?
    fun update(favoritePokemonDTO: FavoritePokemonDTO): Completable
    fun delete(pokemonName: String): Completable
    fun getHungryPokemons(): Single<List<PokemonEntity>>
    fun getSadPokemons(): Single<List<PokemonEntity>>
}

class FavoritePokemonRepository @Inject constructor(private val pokemonDao: FavoritePokemonDao) :
    FavoritePokemonRepositoryDao {
    override fun getAllFavoritePokemons(): Single<List<PokemonEntity>> =
        pokemonDao.loadAllFavoritePokemon().subscribeOn(Schedulers.io())

    override fun insert(pokemon: PokemonDTO): Completable = pokemonDao.addPokemon(
        pokemonEntity = PokemonEntity(
            name = pokemon.name,
            url = pokemon.sprites.frontDefault ?: ""
        )
    ).observeOn(Schedulers.io()).subscribeOn(Schedulers.io())

    override fun getPokemon(pokemonName: String): PokemonEntity =
        pokemonDao.loadPokemon(pokemonName = pokemonName)

    override fun update(favoritePokemonDTO: FavoritePokemonDTO): Completable =
        pokemonDao.updatePokemon(
            pokemonName = favoritePokemonDTO.name,
            hpIndicator = favoritePokemonDTO.hpIndicator,
            funIndicator = favoritePokemonDTO.funIndicator,
            foodIndicator = favoritePokemonDTO.foodIndicator,
            lastTimeGaming = favoritePokemonDTO.lastTimeGaming,
            lastTimeFeeding = favoritePokemonDTO.lastTimeFeeding
        ).subscribeOn(Schedulers.io())

    override fun delete(pokemonName: String): Completable =
        pokemonDao.deletePokemon(pokemonName).subscribeOn(Schedulers.io())

    override fun getHungryPokemons(): Single<List<PokemonEntity>> =
        pokemonDao.getHungryPokemons().subscribeOn(Schedulers.io())

    override fun getSadPokemons(): Single<List<PokemonEntity>> =
        pokemonDao.getSadPokemons().subscribeOn(Schedulers.io())
}