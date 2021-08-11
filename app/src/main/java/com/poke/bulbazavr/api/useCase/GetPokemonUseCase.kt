package com.poke.bulbazavr.api.useCase

import android.util.Log
import com.poke.bulbazavr.api.PokeApiService
import com.poke.bulbazavr.api.data.responses.PokemonResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class GetPokemonUseCase @Inject constructor(
    private val pokeApiService: PokeApiService
) : BaseUseCase<String, Single<PokemonResponse>> {
    override fun invoke(
        params: String,
    ): Single<PokemonResponse> {
        return pokeApiService.getPokemon(params)
            .map { response ->
                response.copy(
                    abilities = Observable.fromIterable(response.abilities)
                        .flatMap { ability ->
                            pokeApiService.getAbilityInfo(ability.ability.url).map {
                                ability.copy(abilityInfo = it)
                            }.toObservable()
                        }
                        .toList()
                        .blockingGet()

                )
            }
            .subscribeOn(Schedulers.io())
            .doOnError { Log.d("POKEMON_INFO", "error = $it") }

    }
}