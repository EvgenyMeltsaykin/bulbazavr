package com.poke.bulbazavr.api.useCase

import com.poke.bulbazavr.api.PokeApiService
import com.poke.bulbazavr.api.data.responses.BaseResponse
import com.poke.bulbazavr.data.Pokemon
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class GetPokemonsUseCase @Inject constructor(
    private val pokeApiService: PokeApiService
) : BaseUseCase<Unit, BaseResponse<Pokemon>> {
    override fun invoke(
        params: Unit,
        onSuccess: (BaseResponse<Pokemon>) -> Unit,
        onFailed: (Throwable) -> Unit
    ) {
        pokeApiService.getPokemons().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it)
            }, {
                onFailed(it)
            })
    }
}

class GetNextPagePokemonsUseCase @Inject constructor(
    private val pokeApiService: PokeApiService
) : BaseUseCase<String, BaseResponse<Pokemon>> {
    override fun invoke(
        params: String,
        onSuccess: (BaseResponse<Pokemon>) -> Unit,
        onFailed: (Throwable) -> Unit
    ) {
        pokeApiService.getPokemons(params).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it)
            }, {
                onFailed(it)
            })
    }
}

