package com.poke.bulbazavr.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String = "",
    val urlPhoto: String
)