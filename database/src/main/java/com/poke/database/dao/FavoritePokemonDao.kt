package com.poke.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.poke.core.data.entity.PokemonEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface FavoritePokemonDao {

    @Query("SELECT * FROM ${PokemonEntity.TABLE_NAME}")
    fun loadAllFavoritePokemon(): Single<List<PokemonEntity>>

    @Query("SELECT * FROM ${PokemonEntity.TABLE_NAME} WHERE :pokemonName like name")
    fun loadPokemon(pokemonName: String): PokemonEntity

    @Insert(entity = PokemonEntity::class, onConflict = OnConflictStrategy.IGNORE)
    fun addPokemon(pokemonEntity: PokemonEntity): Completable

    @Query("DELETE FROM ${PokemonEntity.TABLE_NAME} WHERE :pokemonName like name")
    fun deletePokemon(pokemonName: String): Completable

    @Query("UPDATE ${PokemonEntity.TABLE_NAME} SET hpIndicator = :hpIndicator, foodIndicator = :foodIndicator,funIndicator = :funIndicator, lastTimeFeeding = :lastTimeFeeding , lastTimeGaming = :lastTimeGaming WHERE :pokemonName like name")
    fun updatePokemon(
        pokemonName: String,
        hpIndicator: Int,
        foodIndicator: Int,
        funIndicator: Int,
        lastTimeFeeding: Long,
        lastTimeGaming: Long,
    ): Completable

    @Query("UPDATE ${PokemonEntity.TABLE_NAME} SET foodIndicator = foodIndicator + 1, hpIndicator = (foodIndicator + funIndicator)/2 + 1 , lastTimeFeeding = :currentTime WHERE :pokemonName like name AND foodIndicator <> 100")
    fun plusFoodIndicator(pokemonName: String, currentTime: Long): Completable

    @Query("UPDATE ${PokemonEntity.TABLE_NAME} SET funIndicator = funIndicator + 1, hpIndicator = (foodIndicator + funIndicator)/2 + 1, lastTimeGaming = :currentTime WHERE :pokemonName like name AND funIndicator <> 100")
    fun plusFunIndicator(pokemonName: String, currentTime: Long): Completable

    @Query("UPDATE ${PokemonEntity.TABLE_NAME} SET foodIndicator = foodIndicator - :countFood, hpIndicator = (foodIndicator + funIndicator)/2 + 1  WHERE :pokemonName like name")
    fun minusFoodIndicator(pokemonName: String, countFood: Int): Completable

    @Query("UPDATE ${PokemonEntity.TABLE_NAME} SET funIndicator = funIndicator - :countFun, hpIndicator = (foodIndicator + funIndicator)/2 + 1  WHERE :pokemonName like name")
    fun minusFunIndicator(pokemonName: String, countFun: Int): Completable

    @Query("SELECT * FROM ${PokemonEntity.TABLE_NAME} WHERE foodIndicator < 10")
    fun getHungryPokemons(): Single<List<PokemonEntity>>


    @Query("SELECT * FROM ${PokemonEntity.TABLE_NAME} WHERE funIndicator < 10")
    fun getSadPokemons(): Single<List<PokemonEntity>>

}