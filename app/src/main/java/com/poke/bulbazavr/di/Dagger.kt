package com.poke.bulbazavr.di

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Room
import com.google.gson.Gson
import com.poke.bulbazavr.database.FavoritePokemonDatabase
import com.poke.bulbazavr.database.repositories.FavoritePokemonRepository
import com.poke.bulbazavr.feature.pokeDetailScreen.PokeDetailFragment
import com.poke.bulbazavr.feature.pokeFavoritesScreen.PokeFavoritesFragment
import com.poke.bulbazavr.feature.pokeListScreen.PokeListFragment
import com.poke.bulbazavr.feature.pokeTamagochiScreen.TamagochiFragment
import com.poke.bulbazavr.services.TamagochiService
import com.poke.bulbazavr.services.job.TamagochiJobService
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(pokeListFragment: PokeListFragment)
    fun inject(pokeDetailFragment: PokeDetailFragment)
    fun inject(pokeFavoritesFragment: PokeFavoritesFragment)
    fun inject(tamagochiFragment: TamagochiFragment)
    fun inject(tamagochiService: TamagochiService)
    fun inject(tamagochiJobService: TamagochiJobService)
}

@Module(includes = [NetworkModule::class, AppBindModule::class, DatabaseModule::class, RoomModule::class, ServiceModule::class])
class AppModule(@NonNull context: Context) {
    private val appContext = context

    @Provides
    @Singleton
    @NonNull
    fun provideContext(): Context = this.appContext

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    /*
    @Provides
    fun provideAnimationManagerImpl() : AnimationManagerImpl = AnimationManagerImpl()

     */
}

@Module
class ServiceModule() {
    @Provides
    fun provideTamagochiService(): TamagochiService {
        return TamagochiService()
    }

}

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideFavoritePokeRepository(roomDatabase: FavoritePokemonDatabase): FavoritePokemonRepository =
        FavoritePokemonRepository(roomDatabase.favoritePokemonDao())
}


@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(appContext: Context): FavoritePokemonDatabase = Room.databaseBuilder(
        appContext,
        FavoritePokemonDatabase::class.java,
        "favorite_pokemon_room_database"
    ).build()
}

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun providePokeApiService(): com.poke.api.PokeApiService {
        return com.poke.api.PokeApiService.create()
    }

    @Provides
    fun getPokemonsUseCase(
        pokeApiService: com.poke.api.PokeApiService
    ): com.poke.api.useCase.GetPokemonsUseCase =
        com.poke.api.useCase.GetPokemonsUseCase(pokeApiService)


    @Provides
    fun getPokemonUseCase(
        pokeApiService: com.poke.api.PokeApiService
    ): com.poke.api.useCase.GetPokemonUseCase =
        com.poke.api.useCase.GetPokemonUseCase(pokeApiService)

}

@Module
interface AppBindModule {

    /*
    @Binds
    @Singleton
    fun bindAnimationManager(animationManagerImpl: AnimationManagerImpl):AnimationManager

     */
}


