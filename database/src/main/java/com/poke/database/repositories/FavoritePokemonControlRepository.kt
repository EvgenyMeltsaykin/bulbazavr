package com.poke.database.repositories

import com.poke.database.dao.FavoritePokemonDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

interface FavoritePokemonControlRepositoryDao {
    fun plusFoodIndicator(pokemonName: String, currentTime: Long): Completable
    fun plusFunIndicator(pokemonName: String, currentTime: Long): Completable
    fun minusFoodIndicator(pokemonName: String, countFood: Int): Completable
    fun minusFunIndicator(pokemonName: String, countFun: Int): Completable
}

class FavoritePokemonControlRepository @Inject constructor(private val pokemonDao: FavoritePokemonDao) :
    FavoritePokemonControlRepositoryDao {

    override fun plusFoodIndicator(pokemonName: String, currentTime: Long): Completable =
        pokemonDao.plusFoodIndicator(pokemonName, currentTime).subscribeOn(Schedulers.io())

    override fun plusFunIndicator(pokemonName: String, currentTime: Long): Completable =
        pokemonDao.plusFunIndicator(pokemonName, currentTime).subscribeOn(Schedulers.io())

    override fun minusFoodIndicator(pokemonName: String, countFood: Int): Completable =
        pokemonDao.minusFoodIndicator(pokemonName, countFood).subscribeOn(Schedulers.io())

    override fun minusFunIndicator(pokemonName: String, countFun: Int): Completable =
        pokemonDao.minusFunIndicator(pokemonName, countFun).subscribeOn(Schedulers.io())
}