package com.poke.bulbazavr.api.useCase

import com.poke.bulbazavr.api.PokeApiService
import com.poke.bulbazavr.api.data.responses.PokemonResponse
import com.poke.bulbazavr.api.data.responses.SpriteResponse
import javax.inject.Inject


class GetPokemonUseCase @Inject constructor(
    private val pokeApiService: PokeApiService
) : BaseUseCase<PokemonResponse, SpriteResponse> {
    override fun invoke(
        params: PokemonResponse,
    ): SpriteResponse {
        TODO()
        /*pokeApiService.getPokemon(params.name).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                onSuccess(it)
            }
            .doOnError {
                onFailed(it)
            }

         */

    }

}