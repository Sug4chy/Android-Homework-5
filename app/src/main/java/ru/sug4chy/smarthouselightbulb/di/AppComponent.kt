package ru.sug4chy.smarthouselightbulb.di

import dagger.Component
import ru.sug4chy.smarthouselightbulb.presenter.main.MainFragment

@Component(
    modules = [
        AppModule::class
    ]
)
@AppComponentScope
interface AppComponent {
    fun inject(fragment: MainFragment)
}

