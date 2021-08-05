package com.poke.bulbazavr.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.poke.bulbazavr.database.data.PokemonEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface FavoritePokemonDao {

    @Query("SELECT * FROM ${PokemonEntity.TABLE_NAME}")
    fun loadAllFavoritePokemon(): Single<List<PokemonEntity>>

    @Query("SELECT * FROM ${PokemonEntity.TABLE_NAME} WHERE :pokemonName like name")
    fun loadPokemon(pokemonName: String): Single<PokemonEntity>

    @Insert(entity = PokemonEntity::class, onConflict = OnConflictStrategy.IGNORE)
    fun addPokemon(pokemonEntity: PokemonEntity): Completable

    @Query("DELETE FROM ${PokemonEntity.TABLE_NAME} WHERE :pokemonName like name")
    fun deletePokemon(pokemonName: String): Completable
}