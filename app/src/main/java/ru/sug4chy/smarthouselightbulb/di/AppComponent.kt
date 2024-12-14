package ru.sug4chy.smarthouselightbulb.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.sug4chy.smarthouselightbulb.presenter.main.MainFragment

@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {
    fun inject(fragment: MainFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }
}

