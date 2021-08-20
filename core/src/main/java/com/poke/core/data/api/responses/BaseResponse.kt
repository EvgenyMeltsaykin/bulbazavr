package com.poke.core.data.api.responses

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class BaseResponse<T>(
    @SerializedName("results") val results: List<T> = listOf(),
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
)

@Serializable
data class NamedAPIResourceResponse(
    @SerializedName("name") val name: String = "",
    @SerializedName("url") val url: String = "",
)