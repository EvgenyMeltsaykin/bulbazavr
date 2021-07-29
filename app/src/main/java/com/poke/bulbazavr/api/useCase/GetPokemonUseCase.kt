package com.poke.bulbazavr.api.useCase

import com.poke.bulbazavr.api.PokeApiService
import com.poke.bulbazavr.api.data.responses.PokemonResponse
import com.poke.bulbazavr.api.data.responses.Sprite
import javax.inject.Inject


class GetPokemonUseCase @Inject constructor(
    private val pokeApiService: PokeApiService
) : BaseUseCase<PokemonResponse, Sprite> {
    override fun invoke(
        params: PokemonResponse,
        onSuccess: (Sprite) -> Unit,
        onFailed: (Throwable) -> Unit
    ) {

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