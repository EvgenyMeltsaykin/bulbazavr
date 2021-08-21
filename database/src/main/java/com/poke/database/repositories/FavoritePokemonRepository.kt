package com.poke.database.repositories

import com.poke.core.data.dto.FavoritePokemonDTO
import com.poke.core.data.dto.PokemonDTO
import com.poke.core.data.entity.PokemonEntity
import com.poke.database.dao.FavoritePokemonDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

interface FavoritePokemonRepositoryDao {
    fun getAllFavoritePokemon(): Single<List<PokemonEntity>>
    fun insert(pokemon: PokemonDTO): Completable
    fun getPokemon(pokemonName: String): Single<PokemonEntity>
    fun update(favoritePokemonDTO: FavoritePokemonDTO): Completable
    fun plusFoodIndicator(pokemonName: String): Completable
    fun plusFunIndicator(pokemonName: String): Completable
    fun minusFoodIndicator(): Completable
    fun minusFunIndicator(): Completable
    fun delete(pokemonName: String): Completable
    fun getHungryPokemons(): Single<List<PokemonEntity>>
    fun getSadPokemons(): Single<List<PokemonEntity>>
}

class FavoritePokemonRepository(private val pokemonDao: FavoritePokemonDao) :
    FavoritePokemonRepositoryDao {
    override fun getAllFavoritePokemon(): Single<List<PokemonEntity>> =
        pokemonDao.loadAllFavoritePokemon().subscribeOn(Schedulers.io())

    override fun insert(pokemon: PokemonDTO): Completable = pokemonDao.addPokemon(
        pokemonEntity = PokemonEntity(
            name = pokemon.name,
            url = pokemon.sprites.frontDefault ?: ""
        )
    ).observeOn(Schedulers.io()).subscribeOn(Schedulers.io())

    override fun getPokemon(pokemonName: String): Single<PokemonEntity> =
        pokemonDao.loadPokemon(pokemonName = pokemonName).subscribeOn(Schedulers.io())

    override fun update(favoritePokemonDTO: FavoritePokemonDTO): Completable =
        pokemonDao.updatePokemon(
            pokemonName = favoritePokemonDTO.name,
            hpIndicator = favoritePokemonDTO.hpIndicator,
            funIndicator = favoritePokemonDTO.funIndicator,
            foodIndicator = favoritePokemonDTO.foodIndicator
        ).subscribeOn(Schedulers.io())

    override fun plusFoodIndicator(pokemonName: String): Completable =
        pokemonDao.plusFoodIndicator(pokemonName).subscribeOn(Schedulers.io())

    override fun plusFunIndicator(pokemonName: String): Completable =
        pokemonDao.plusFunIndicator(pokemonName).subscribeOn(Schedulers.io())

    override fun minusFoodIndicator(): Completable =
        pokemonDao.minusFoodIndicator().subscribeOn(Schedulers.io())

    override fun minusFunIndicator(): Completable =
        pokemonDao.minusFunIndicator().subscribeOn(Schedulers.io())

    override fun delete(pokemonName: String): Completable =
        pokemonDao.deletePokemon(pokemonName).subscribeOn(Schedulers.io())

    override fun getHungryPokemons(): Single<List<PokemonEntity>> =
        pokemonDao.getHungryPokemons().subscribeOn(Schedulers.io())

    override fun getSadPokemons(): Single<List<PokemonEntity>> =
        pokemonDao.getSadPokemons().subscribeOn(Schedulers.io())
}