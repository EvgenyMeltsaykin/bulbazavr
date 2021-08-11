package com.poke.bulbazavr.di

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Room
import com.google.gson.Gson
import com.poke.bulbazavr.api.PokeApiService
import com.poke.bulbazavr.api.useCase.GetPokemonUseCase
import com.poke.bulbazavr.api.useCase.GetPokemonsUseCase
import com.poke.bulbazavr.database.FavoritePokemonDatabase
import com.poke.bulbazavr.database.repositories.FavoritePokemonRepository
import com.poke.bulbazavr.feature.pokeDetailScreen.PokeDetailFragment
import com.poke.bulbazavr.feature.pokeFavoritesScreen.PokeFavoritesFragment
import com.poke.bulbazavr.feature.pokeListScreen.PokeListFragment
import com.poke.bulbazavr.feature.pokeTamagochiScreen.TamagochiFragment
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
}

@Module(includes = [NetworkModule::class, AppBindModule::class, DatabaseModule::class, RoomModule::class])
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
    fun providePokeApiService(): PokeApiService {
        return PokeApiService.create()
    }

    @Provides
    fun getPokemonsUseCase(
        pokeApiService: PokeApiService
    ): GetPokemonsUseCase = GetPokemonsUseCase(pokeApiService)


    @Provides
    fun getPokemonUseCase(
        pokeApiService: PokeApiService
    ): GetPokemonUseCase = GetPokemonUseCase(pokeApiService)

}

@Module
interface AppBindModule {

    /*
    @Binds
    @Singleton
    fun bindAnimationManager(animationManagerImpl: AnimationManagerImpl):AnimationManager

     */
}


