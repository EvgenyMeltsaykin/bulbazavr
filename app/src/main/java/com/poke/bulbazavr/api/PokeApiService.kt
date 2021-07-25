package com.poke.bulbazavr.api

import com.poke.bulbazavr.data.BaseResponse
import com.poke.bulbazavr.data.Pokemon
import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url


interface PokeApiService {
    @GET("pokemon")
    fun getPokemons(): Observable<BaseResponse<Pokemon>>

    @GET
    fun getPokemons(@Url url: String): Observable<BaseResponse<Pokemon>>

    companion object Factory {

        fun create(): PokeApiService {
            var interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = retrofit2.Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://pokeapi.co/api/v2/")
                .build()
            return retrofit.create(PokeApiService::class.java)
        }
    }
}