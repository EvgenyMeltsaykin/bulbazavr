package com.poke.bulbazavr.data

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val name: String,
    val urlPhoto:String = ""
)