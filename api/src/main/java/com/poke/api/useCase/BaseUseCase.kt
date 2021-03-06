package com.poke.api.useCase

interface BaseUseCase<REQUEST, RESPONSE> {
    operator fun invoke(
        params: REQUEST,
    ): RESPONSE
}