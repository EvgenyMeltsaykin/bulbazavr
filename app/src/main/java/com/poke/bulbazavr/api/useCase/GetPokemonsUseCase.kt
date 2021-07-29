package com.poke.bulbazavr.api.useCase

import com.poke.bulbazavr.api.PokeApiService
import com.poke.bulbazavr.api.data.request.OffsetLimitRequest
import com.poke.bulbazavr.api.data.responses.BaseResponse
import com.poke.bulbazavr.api.data.responses.PokemonResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class GetPokemonsUseCase @Inject constructor(
    private val pokeApiService: PokeApiService
) : BaseUseCase<OffsetLimitRequest, BaseResponse<PokemonResponse>> {
    override fun invoke(
        params: OffsetLimitRequest,
        onSuccess: (BaseResponse<PokemonResponse>) -> Unit,
        onFailed: (Throwable) -> Unit
    ) {
        pokeApiService.getPokemons(params.offset)
            .observeOn(AndroidSchedulers.mainThread())
            .map { response ->
                onSuccess(
                    response.copy(
                        results = Observable.fromIterable(response.results)
                            .flatMap { pokemon ->
                                pokeApiService.getPokemon(pokemon.name).map {
                                    pokemon.copy(sprites = it.sprites)
                                }.toObservable()
                            }.toList().blockingGet()
                    )
                )
            }
            .doOnError { onFailed(it) }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}

