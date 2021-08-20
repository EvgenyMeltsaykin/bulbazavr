package com.poke.core.data.api.request


data class OffsetLimitRequest(
    val page: Int,
    val limit: Int = 20,
    val offset: Int = page * limit
)
