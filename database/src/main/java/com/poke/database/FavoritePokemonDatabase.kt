package com.poke.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.poke.core.data.entity.PokemonEntity
import com.poke.database.dao.FavoritePokemonDao

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = true)
abstract class FavoritePokemonDatabase : RoomDatabase() {

    abstract fun favoritePokemonDao(): FavoritePokemonDao
}