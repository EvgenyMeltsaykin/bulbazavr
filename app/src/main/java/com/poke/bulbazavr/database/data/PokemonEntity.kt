package com.poke.bulbazavr.database.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.poke.bulbazavr.database.data.PokemonEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class PokemonEntity(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "url")
    val url: String = "",
    /*@ColumnInfo(name ="sprites")
    val sprites: SpriteResponse,
    @ColumnInfo(name ="stats")
    val stats: List<StatResponse>?,

     */
) {
    companion object {
        const val TABLE_NAME = "favorite_pokemon_table"
    }
}