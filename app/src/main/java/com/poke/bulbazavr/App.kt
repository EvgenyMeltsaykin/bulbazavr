package com.poke.bulbazavr

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.content.ContextCompat.getSystemService
import com.poke.bulbazavr.di.AppComponent
import com.poke.bulbazavr.di.AppModule
import com.poke.bulbazavr.di.DaggerAppComponent

class App : Application() {
    companion object {
        const val TAMAGOCHI_CHANNEL_ID = "TAMAGOCHI SERVICE CHANNEL"
        const val STAT_CHANNEL_ID = "STAT CHANNEL"
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this.applicationContext))
            .build()
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val tamagochiServiceChannel = NotificationChannel(
                TAMAGOCHI_CHANNEL_ID,
                TAMAGOCHI_CHANNEL_ID,
                NotificationManager.IMPORTANCE_LOW
            )

            val statChannel = NotificationChannel(
                STAT_CHANNEL_ID,
                STAT_CHANNEL_ID,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(this.applicationContext, NotificationManager::class.java)
            manager?.createNotificationChannel(tamagochiServiceChannel)
            manager?.createNotificationChannel(statChannel)
        }
    }
}


val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }