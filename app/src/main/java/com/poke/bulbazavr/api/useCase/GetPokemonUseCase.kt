package com.poke.bulbazavr.api.useCase

import com.poke.bulbazavr.api.PokeApiService
import com.poke.bulbazavr.api.data.responses.PokemonResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class GetPokemonUseCase @Inject constructor(
    private val pokeApiService: PokeApiService
) : BaseUseCase<PokemonResponse, Single<PokemonResponse>> {
    override fun invoke(
        params: PokemonResponse,
    ): Single<PokemonResponse> {
        TODO()
        /*
        return pokeApiService.getPokemon(params.name).subscribeOn(Schedulers.io())
            .doOnSuccess {
                onSuccess(it)
            }
            .doOnError {
                onFailed(it)
            }
         */

    }

}