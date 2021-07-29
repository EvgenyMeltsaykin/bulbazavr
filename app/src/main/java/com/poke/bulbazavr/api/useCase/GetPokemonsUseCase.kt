package com.poke.bulbazavr.api.useCase

import com.poke.bulbazavr.api.PokeApiService
import com.poke.bulbazavr.api.data.request.OffsetLimitRequest
import com.poke.bulbazavr.api.data.responses.BaseResponse
import com.poke.bulbazavr.data.Pokemon
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class GetPokemonsUseCase @Inject constructor(
    private val pokeApiService: PokeApiService
) : BaseUseCase<OffsetLimitRequest, BaseResponse<Pokemon>> {
    override fun invoke(
        params: OffsetLimitRequest,
        onSuccess: (BaseResponse<Pokemon>) -> Unit,
        onFailed: (Throwable) -> Unit
    ) {
        pokeApiService.getPokemons(params.offset)
            .map { response ->
                onSuccess(
                    response.copy(
                        results = Observable.fromIterable(response.results)
                            .concatMap { pokemon ->
                                pokeApiService.getPokemon(pokemon.name).map {
                                    pokemon.copy(urlPhoto = it.sprites.backDefault)
                                }.toObservable()
                            }
                            .toList()
                    )
                )
            }
            .doOnError { onFailed(it) }
            .subscribeOn(Schedulers.io())

    }
}

