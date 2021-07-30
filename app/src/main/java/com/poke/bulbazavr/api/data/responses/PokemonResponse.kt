package com.poke.bulbazavr.api.data.responses

import com.google.gson.annotations.SerializedName
import com.poke.bulbazavr.data.PokemonDTO
import com.poke.bulbazavr.data.SpriteDTO
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String = "",
    @SerializedName("sprites") val sprites: SpriteResponse,
) {
    fun toPokemonDTO(): PokemonDTO = PokemonDTO(
        name = this.name,
        sprites = this.sprites.toSpriteDTO()
    )
}

@Serializable
data class SpriteResponse(
    @SerializedName("back_default") val backDefault: String? = "",
    @SerializedName("back_female") val backFemale: String? = "",
    @SerializedName("back_shiny") val backShiny: String? = "",
    @SerializedName("back_shiny_female") val backShinyFemale: String? = "",
    @SerializedName("front_default") val frontDefault: String? = "",
    @SerializedName("front_female") val frontFemale: String? = "",
    @SerializedName("front_shiny") val frontShiny: String? = "",
    @SerializedName("front_shiny_female") val frontShinyFemale: String? = ""
) {
    fun toSpriteDTO(): SpriteDTO = SpriteDTO(
        backDefault = this.backDefault,
        backFemale = this.backFemale,
        backShiny = this.backShiny,
        backShinyFemale = this.backShinyFemale,
        frontDefault = this.frontDefault,
        frontFemale = this.frontFemale,
        frontShiny = this.frontShiny,
        frontShinyFemale = this.frontShinyFemale
    )
}

