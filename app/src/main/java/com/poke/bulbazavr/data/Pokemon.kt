package com.poke.bulbazavr.data

data class PokemonDTO(
    val name: String,
    val sprites: SpriteDTO,
    var stats: List<StatDTO>?
)

data class SpriteDTO(
    val backDefault: String? = "",
    val backFemale: String? = "",
    val backShiny: String? = "",
    val backShinyFemale: String? = "",
    val frontDefault: String? = "",
    val frontFemale: String? = "",
    val frontShiny: String? = "",
    val frontShinyFemale: String? = ""
)

data class StatDTO(
    val effort: Int = 0,
    val baseStat: Int = 0,
    val statName: String
)
