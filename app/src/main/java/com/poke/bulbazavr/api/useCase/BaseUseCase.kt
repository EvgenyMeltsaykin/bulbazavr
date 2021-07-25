package com.poke.bulbazavr.api.useCase

interface BaseUseCase<REQUEST, RESPONSE> {
    operator fun invoke(
        params: REQUEST,
        onSuccess: (RESPONSE) -> Unit,
        onFailed: (Throwable) -> Unit
    )
}