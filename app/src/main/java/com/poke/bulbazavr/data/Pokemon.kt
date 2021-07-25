package com.poke.bulbazavr.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("results") val results: ArrayList<T> = arrayListOf(),
    @SerialName("count") val count: Int,
    @SerialName("next") val next: String?,
    @SerialName("previous") val previous: String?,
)

@Serializable
data class Pokemon(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String = ""
)