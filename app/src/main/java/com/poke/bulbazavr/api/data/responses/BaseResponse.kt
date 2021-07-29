package com.poke.bulbazavr.api.data.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("results") val results: List<T> = listOf(),
    @SerialName("count") val count: Int,
    @SerialName("next") val next: String?,
    @SerialName("previous") val previous: String?,
)

@Serializable
data class Sprites(
    @SerialName("sprites") val sprites: Sprite
)

@Serializable
data class Sprite(
    @SerialName("back_default") val backDefault: String
)
