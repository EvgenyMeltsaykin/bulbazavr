package com.poke.bulbazavr.database.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.poke.bulbazavr.data.FavoritePokemonDTO
import com.poke.bulbazavr.database.data.PokemonEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class PokemonEntity(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "url")
    val url: String = "",
    @ColumnInfo(name = "foodIndicator")
    val foodIndicator: Int = 0,
    @ColumnInfo(name = "funIndicator")
    val funIndicator: Int = 0,
    @ColumnInfo(name = "hpIndicator")
    val hpIndicator: Int = 0,
) {
    companion object {
        const val TABLE_NAME = "favorite_pokemon_table"
    }

    fun toFavoritePokemonDTO(): FavoritePokemonDTO = FavoritePokemonDTO(
        name = this.name,
        url = this.url,
        foodIndicator = this.foodIndicator,
        funIndicator = this.funIndicator,
        hpIndicator = this.hpIndicator
    )
}