package com.poke.bulbazavr.di

import com.poke.bulbazavr.MainActivity
import com.poke.bulbazavr.api.PokeApiService
import com.poke.bulbazavr.feature.pokeListScreen.PokeListFragment
import com.poke.bulbazavr.feature.pokeListScreen.PokeListPresenter
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(pokeListFragment: PokeListFragment)
    fun inject(mainActivity: MainActivity)

    val pokeApi:PokeApiService
}

@Module(includes = [NetworkModule::class, AppBindModule::class])
class AppModule

@Module
class NetworkModule {
    @Provides
    fun providePokeApiService():PokeApiService {
        return PokeApiService.create()
    }
}

@Module
interface AppBindModule{

}


