package ru.sug4chy.smarthouselightbulb

import android.app.Application
import ru.sug4chy.smarthouselightbulb.di.AppComponent
import ru.sug4chy.smarthouselightbulb.di.DaggerAppComponent

class MyApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .create()
    }

}
