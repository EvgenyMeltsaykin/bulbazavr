package com.poke.bulbazavr

import android.app.Application
import android.content.Context
import com.poke.bulbazavr.di.AppComponent
import com.poke.bulbazavr.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}