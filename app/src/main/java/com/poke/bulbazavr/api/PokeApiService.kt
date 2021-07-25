package com.poke.bulbazavr.api

import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface PokeApiService {
    fun getString():String = "Hello"

    companion object Factory{
        fun create(): PokeApiService {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("Url")
                .build()
            return retrofit.create(PokeApiService::class.java)
        }
    }
}