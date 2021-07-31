package com.poke.bulbazavr.api.useCase

import com.poke.bulbazavr.api.PokeApiService
import com.poke.bulbazavr.api.data.responses.PokemonResponse
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class GetPokemonUseCase @Inject constructor(
    private val pokeApiService: PokeApiService
) : BaseUseCase<String, Single<PokemonResponse>> {
    override fun invoke(
        params: String,
    ): Single<PokemonResponse> {
        return pokeApiService.getPokemon(params).subscribeOn(Schedulers.io())
    }
}