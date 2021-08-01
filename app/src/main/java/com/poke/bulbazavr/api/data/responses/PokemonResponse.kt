package com.poke.bulbazavr.api.data.responses

import com.google.gson.annotations.SerializedName
import com.poke.bulbazavr.data.*
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String? = "",
    @SerializedName("sprites") val sprites: SpriteResponse,
    @SerializedName("stats") val stats: List<StatResponse>?,
    @SerializedName("abilities") val abilities: List<AbilityResponse>?,
) {
    fun toPokemonDTO(): PokemonDTO = PokemonDTO(
        name = this.name,
        sprites = this.sprites.toSpriteDTO(),
        stats = InfoVisible(info = this.stats?.map { it.toStatDTO() }),
        abilities = InfoVisible(info = this.abilities?.map { it.toAbilityDTO() })
    )
}

@Serializable
data class AbilityResponse(
    @SerializedName("is_hidden") val isHidden: Boolean = true,
    @SerializedName("slot") val slot: Int = 0,
    @SerializedName("ability") val ability: NamedAPIResourceResponse,
    val abilityInfo: AbilityInfoResponse
) {
    fun toAbilityDTO(): AbilityDTO = AbilityDTO(
        name = this.abilityInfo.name,
        effectInfo = this.abilityInfo.effectEntries.find { it.language.name == "en" }?.toEffectDTO()
    )
}

@Serializable
data class AbilityInfoResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("effect_entries") val effectEntries: List<EffectResponse>,
)

@Serializable
data class EffectResponse(
    @SerializedName("effect") val effect: String,
    @SerializedName("short_effect") val shortEffect: String,
    @SerializedName("language") val language: NamedAPIResourceResponse,
) {
    fun toEffectDTO(): EffectDTO = EffectDTO(
        effect = this.effect,
        shortEffect = this.shortEffect
    )
}

@Serializable
data class StatResponse(
    @SerializedName("effort") val effort: Int = 0,
    @SerializedName("base_stat") val baseStat: Int = 0,
    @SerializedName("stat") val stat: NamedAPIResourceResponse
) {
    fun toStatDTO(): StatDTO = StatDTO(
        effort = this.effort,
        baseStat = this.baseStat,
        statName = this.stat.name
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

