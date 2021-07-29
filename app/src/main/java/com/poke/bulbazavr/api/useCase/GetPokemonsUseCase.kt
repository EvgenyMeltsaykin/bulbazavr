package com.poke.bulbazavr.api.useCase

import com.poke.bulbazavr.api.PokeApiService
import com.poke.bulbazavr.api.data.request.OffsetLimitRequest
import com.poke.bulbazavr.api.data.responses.BaseResponse
import com.poke.bulbazavr.data.Pokemon
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
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

        pokeApiService.getPokemons(params.offset).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { response ->
                val pokemons: MutableList<Pokemon> = mutableListOf()
                response.results.forEach { pokemon ->
                    val infoPokemon = pokeApiService.getPokemon(pokemon.name)
                    infoPokemon.doOnSuccess {
                        pokemons.add(pokemon.copy(urlPhoto = it.sprites.backDefault))
                    }
                }
                onSuccess(response.copy(results = pokemons))
            }
            .doOnError {
                onFailed(it)
            }

    }
}

