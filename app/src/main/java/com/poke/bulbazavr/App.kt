package com.poke.bulbazavr

import android.app.Application
import android.content.Context
import com.poke.bulbazavr.di.AppComponent
import com.poke.bulbazavr.di.AppModule
import com.poke.bulbazavr.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this.applicationContext))
            .build()
    }

}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }