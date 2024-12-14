package ru.sug4chy.smarthouselightbulb.di

import dagger.Module
import ru.sug4chy.smarthouselightbulb.di.binds.RepositoryBindsModule
import ru.sug4chy.smarthouselightbulb.di.binds.UseCaseBindsModule
import ru.sug4chy.smarthouselightbulb.di.provides.NetworkProvidesModule
import ru.sug4chy.smarthouselightbulb.di.viewModel.ViewModelModule

@Module(
    includes = [
        ViewModelModule::class,
        UseCaseBindsModule::class,
        RepositoryBindsModule::class,
        NetworkProvidesModule::class
    ]
)
class AppModule