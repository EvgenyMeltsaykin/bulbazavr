package com.poke.bulbazavr.data

data class PokemonDTO(
    val name: String,
    val sprites: SpriteDTO
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
