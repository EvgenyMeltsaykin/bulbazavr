package com.poke.bulbazavr.api.useCase

import com.poke.bulbazavr.api.PokeApiService
import com.poke.bulbazavr.api.data.responses.Sprites
import com.poke.bulbazavr.data.Pokemon
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val pokeApiService: PokeApiService
) : BaseUseCase<Pokemon, Sprites> {
    override fun invoke(
        params: Pokemon,
        onSuccess: (Sprites) -> Unit,
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