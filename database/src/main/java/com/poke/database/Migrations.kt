package com.poke.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.poke.core.data.entity.PokemonEntity

val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE ${PokemonEntity.TABLE_NAME} ADD COLUMN lastTimeFeeding INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE ${PokemonEntity.TABLE_NAME} ADD COLUMN lastTimeGaming INTEGER NOT NULL DEFAULT 0")
    }
}