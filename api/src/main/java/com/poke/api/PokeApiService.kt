package com.poke.api

import com.poke.core.data.api.responses.AbilityInfoResponse
import com.poke.core.data.api.responses.BaseResponse
import com.poke.core.data.api.responses.PokemonResponse
import com.poke.core.utils.Constants.BASE_POKE_API
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url


interface PokeApiService {
    @GET("pokemon")
    fun getPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = 20
    ): Single<BaseResponse<PokemonResponse>>

    @GET("pokemon/{name}")
    fun getPokemon(@Path("name") name: String): Single<PokemonResponse>

    @GET
    fun getAbilityInfo(@Url url: String): Single<AbilityInfoResponse>

    companion object Factory {
        fun create(): PokeApiService {
            val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(logger).build()
            val retrofit = retrofit2.Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_POKE_API)
                .build()
            return retrofit.create(PokeApiService::class.java)
        }
    }
}