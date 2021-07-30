package com.poke.bulbazavr.api.data.request


data class OffsetLimitRequest(
    val page: Int,
    val limit: Int = 20,
    val offset: Int = page * limit
)
