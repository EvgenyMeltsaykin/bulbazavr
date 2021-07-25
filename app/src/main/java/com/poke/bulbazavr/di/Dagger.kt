package com.poke.bulbazavr.di

import com.poke.bulbazavr.api.PokeApiService
import com.poke.bulbazavr.api.useCase.GetNextPagePokemonsUseCase
import com.poke.bulbazavr.api.useCase.GetPokemonsUseCase
import com.poke.bulbazavr.feature.pokeListScreen.PokeListFragment
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(pokeListFragment: PokeListFragment)
}

@Module(includes = [NetworkModule::class, AppBindModule::class])
class AppModule

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun providePokeApiService(): PokeApiService {
        return PokeApiService.create()
    }

    @Provides
    fun getPokemonsUseCase(
        pokeApiService: PokeApiService
    ): GetPokemonsUseCase {
        return GetPokemonsUseCase(pokeApiService)
    }

    @Provides
    fun getNextPagePokemonsUseCase(
        pokeApiService: PokeApiService
    ): GetNextPagePokemonsUseCase {
        return GetNextPagePokemonsUseCase(pokeApiService)
    }
}

@Module
interface AppBindModule{

}


