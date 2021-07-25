package com.poke.bulbazavr.di

import com.poke.bulbazavr.MainActivity
import com.poke.bulbazavr.api.PokeApiService
import com.poke.bulbazavr.feature.pokeListScreen.PokeListFragment
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(pokeListFragment: PokeListFragment)
    fun inject(mainActivity: MainActivity)
}

@Module(includes = [NetworkModule::class, AppBindModule::class])
class AppModule

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun providePokeApiService():PokeApiService {
        return PokeApiService.create()
    }
}

@Module
interface AppBindModule{

}


