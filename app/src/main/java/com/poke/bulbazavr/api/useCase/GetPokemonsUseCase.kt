package com.poke.bulbazavr.api.useCase

import com.poke.bulbazavr.api.PokeApiService
import com.poke.bulbazavr.api.data.request.OffsetLimitRequest
import com.poke.bulbazavr.api.data.responses.BaseResponse
import com.poke.bulbazavr.api.data.responses.PokemonResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class GetPokemonsUseCase @Inject constructor(
    private val pokeApiService: PokeApiService
) : BaseUseCase<OffsetLimitRequest, Single<BaseResponse<PokemonResponse>>> {
    override fun invoke(
        params: OffsetLimitRequest
    ): Single<BaseResponse<PokemonResponse>> {
        return pokeApiService.getPokemons(params.offset)
            .map { response ->
                response.copy(
                    results = Observable.fromIterable(response.results)
                        .flatMap { pokemon ->
                            pokeApiService.getPokemon(pokemon.name).map {
                                pokemon.copy(sprites = it.sprites)
                            }.toObservable()
                        }
                        .toList()
                        .blockingGet()
                )
            }
            .subscribeOn(Schedulers.io())
    }
}

