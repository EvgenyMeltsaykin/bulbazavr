package com.poke.bulbazavr.di

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Room
import com.google.gson.Gson
import com.poke.api.PokeApiService
import com.poke.bulbazavr.feature.pokeDetailScreen.PokeDetailFragment
import com.poke.bulbazavr.feature.pokeFavoritesScreen.PokeFavoritesFragment
import com.poke.bulbazavr.feature.pokeListScreen.PokeListFragment
import com.poke.bulbazavr.feature.pokeTamagochiScreen.TamagochiFragment
import com.poke.bulbazavr.services.TamagochiService
import com.poke.bulbazavr.services.job.TamagochiJobService
import com.poke.database.FavoritePokemonDatabase
import com.poke.database.MIGRATION_1_2
import com.poke.database.dao.FavoritePokemonDao
import com.poke.database.repositories.FavoritePokemonControlRepository
import com.poke.database.repositories.FavoritePokemonControlRepositoryDao
import com.poke.database.repositories.FavoritePokemonRepository
import com.poke.database.repositories.FavoritePokemonRepositoryDao
import com.poke.database.usecases.*
import dagger.Binds
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

@Module(
    includes = [
        NetworkModule::class,
        DatabaseModule::class,
        RoomModule::class,
        RepositoryModule::class,
        UseCasesModule::class]
)
class AppModule(@NonNull context: Context) {
    private val appContext = context

    @Provides
    @Singleton
    @NonNull
    fun provideContext(): Context = this.appContext

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()
}

@Module
abstract class UseCasesModule {
    @Binds
    @Singleton
    abstract fun bindGetFavoritePokemonsUseCase(useCase: GetFavoritePokemonsUseCaseImpl): GetFavoritePokemonsUseCase

    @Binds
    @Singleton
    abstract fun bindGetFavoritePokemonUseCase(useCase: GetFavoritePokemonUseCaseImpl): GetFavoritePokemonUseCase

    @Binds
    @Singleton
    abstract fun bindUpdateFavoritePokemonUseCase(useCase: UpdateFavoritePokemonUseCaseImpl): UpdateFavoritePokemonUseCase

    @Binds
    @Singleton
    abstract fun bindDeleteFavoritePokemonUseCase(useCase: DeleteFavoritePokemonUseCaseImpl): DeleteFavoritePokemonUseCase

    @Binds
    @Singleton
    abstract fun bindAddFavoritePokemonUseCase(useCase: AddFavoritePokemonUseCaseImpl): AddFavoritePokemonUseCase

    @Binds
    @Singleton
    abstract fun bindGetHungryPokemonsUseCase(useCase: GetHungryPokemonsUseCaseImpl): GetHungryPokemonsUseCase

    @Binds
    @Singleton
    abstract fun bindGetSadPokemonsUseCase(useCase: GetSadPokemonsUseCaseImpl): GetSadPokemonsUseCase

    @Binds
    @Singleton
    abstract fun bindAddFoodPokemonUseCase(useCase: AddFoodPokemonUseCaseImpl): AddFoodPokemonUseCase

    @Binds
    @Singleton
    abstract fun bindAddFunPokemonUseCase(useCase: AddFunPokemonUseCaseImpl): AddFunPokemonUseCase

}

@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideFavoritePokeRepository(repository: FavoritePokemonRepository): FavoritePokemonRepositoryDao

    @Binds
    @Singleton
    abstract fun provideFavoritePokemonControlRepository(repository: FavoritePokemonControlRepository): FavoritePokemonControlRepositoryDao
}

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideFavoritePokeDao(roomDatabase: FavoritePokemonDatabase): FavoritePokemonDao =
        roomDatabase.favoritePokemonDao()

}

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(appContext: Context): FavoritePokemonDatabase =
        Room.databaseBuilder(
            appContext,
            FavoritePokemonDatabase::class.java,
            "favorite_pokemon_room_database"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .addMigrations(MIGRATION_1_2)
            .build()
}

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun providePokeApiService(): PokeApiService {
        return PokeApiService.create()
    }

}

