package com.poke.bulbazavr.services

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.poke.bulbazavr.App.Companion.TAMAGOCHI_CHANNEL_ID
import com.poke.bulbazavr.R
import com.poke.bulbazavr.appComponent
import com.poke.bulbazavr.utils.Constants.ID_ALWAYS_NOTIFICATION

class TamagochiService : Service() {

    override fun onCreate() {
        appComponent.inject(this)
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification = initNotification()
        startForeground(ID_ALWAYS_NOTIFICATION,notification)
        return START_STICKY
    }

    private fun initNotification(): Notification {
        return NotificationCompat.Builder(this,TAMAGOCHI_CHANNEL_ID)
            .setContentTitle(getString(R.string.app_name))
            .setContentText(getString(R.string.pokemon_in_pokeball))
            .setSmallIcon(R.drawable.ic_pokeball)
            .build()
    }

    override fun onBind(p0: Intent?): IBinder?  = null
}