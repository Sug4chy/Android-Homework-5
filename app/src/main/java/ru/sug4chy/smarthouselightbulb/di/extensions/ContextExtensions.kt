package ru.sug4chy.smarthouselightbulb.di.extensions

import android.content.Context
import ru.sug4chy.smarthouselightbulb.MyApplication
import ru.sug4chy.smarthouselightbulb.di.AppComponent

val Context.appComponent: AppComponent
    get() = when(this) {
        is MyApplication -> this.appComponent
        else -> applicationContext.appComponent
    }
