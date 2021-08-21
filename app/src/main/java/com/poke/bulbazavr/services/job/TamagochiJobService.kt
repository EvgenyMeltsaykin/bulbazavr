package com.poke.bulbazavr.services.job

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.poke.bulbazavr.App.Companion.STAT_CHANNEL_ID
import com.poke.bulbazavr.MainActivity
import com.poke.bulbazavr.R
import com.poke.bulbazavr.appComponent
import com.poke.core.data.entity.PokemonEntity
import com.poke.core.utils.Constants.ID_HUNGRY_NOTIFICATION
import com.poke.core.utils.Constants.ID_SAD_NOTIFICATION
import com.poke.core.utils.Constants.MINUS_STAT_IN_15_MINUTE
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject


class TamagochiJobService() : JobService() {
    @Inject
    lateinit var pokemonRepository: com.poke.database.repositories.FavoritePokemonRepository
    private var firstInit = true

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        backgroundWork(params)
        return true
    }

    private fun backgroundWork(params: JobParameters?) {
        Log.d("ServiceTest", "backgroundWork")
        /*
        if (firstInit){
            firstInit = false
            jobFinished(params,true)
            return
        }

         */
        for (i in 0 until MINUS_STAT_IN_15_MINUTE) {
            pokemonRepository.minusFoodIndicator().subscribe()
            pokemonRepository.minusFunIndicator().subscribe()
        }
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        pokemonRepository.getHungryPokemons().observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it.isNotEmpty()) {
                        val pokemons = getStringPokemonsName(it)
                        Log.d("ServiceTest", "getHungryPokemons $it")
                        notificationManager.notify(
                            ID_HUNGRY_NOTIFICATION,
                            initNotification(getString(R.string.pokemon_hungry), pokemons)
                        )
                    }
                },
                { }
            )
        pokemonRepository.getSadPokemons().observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it.isNotEmpty()) {
                        val pokemons = getStringPokemonsName(it)
                        Log.d("ServiceTest", "getSadPokemons $it")
                        notificationManager.notify(
                            ID_SAD_NOTIFICATION,
                            initNotification(getString(R.string.pokemon_sad), pokemons)
                        )
                    }
                },
                { }
            )
        jobFinished(params, true)
    }

    private fun initNotification(text: String, pokemons: String): Notification {
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)

        return NotificationCompat.Builder(this, STAT_CHANNEL_ID)
            .setContentTitle(getString(R.string.app_name))
            .setAutoCancel(true)
            .setContentText("$text $pokemons")
            .setSmallIcon(R.drawable.ic_pokeball)
            .setContentIntent(pendingIntent)
            .build()
    }

    private fun getStringPokemonsName(pokemons: List<PokemonEntity>): String {
        var pokemonsString = ""
        pokemons.forEach {
            pokemonsString += it.name + " "
        }
        return pokemonsString
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        return true
    }
}