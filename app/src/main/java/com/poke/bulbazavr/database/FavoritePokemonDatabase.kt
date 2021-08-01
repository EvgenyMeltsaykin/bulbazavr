package com.poke.bulbazavr.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.poke.bulbazavr.database.dao.FavoritePokemonDao
import com.poke.bulbazavr.database.data.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = true)
abstract class FavoritePokemonDatabase : RoomDatabase() {

    abstract fun favoritePokemonDao(): FavoritePokemonDao
}