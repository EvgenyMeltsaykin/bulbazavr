package com.poke.bulbazavr.api.data.responses

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerializedName("results") val results: List<T> = listOf(),
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
)