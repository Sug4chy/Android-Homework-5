package ru.sug4chy.smarthouselightbulb.di.binds

import dagger.Binds
import dagger.Module
import ru.sug4chy.smarthouselightbulb.data.repository.BrightnessRepository
import ru.sug4chy.smarthouselightbulb.data.repository.ColorRepository
import ru.sug4chy.smarthouselightbulb.data.repository.LightbulbStateRepository
import ru.sug4chy.smarthouselightbulb.data.repository.implementation.BrightnessRepositoryImpl
import ru.sug4chy.smarthouselightbulb.data.repository.implementation.ColorRepositoryImpl
import ru.sug4chy.smarthouselightbulb.data.repository.implementation.LightbulbStateRepositoryImpl
import ru.sug4chy.smarthouselightbulb.di.AppComponentScope

@Module
internal interface RepositoryBindsModule {

    @Binds
    @AppComponentScope
    fun bindLightbulbStateRepository(impl: LightbulbStateRepositoryImpl): LightbulbStateRepository

    @Binds
    @AppComponentScope
    fun bindBrightnessRepository(impl: BrightnessRepositoryImpl): BrightnessRepository

    @Binds
    @AppComponentScope
    fun bindColorRepository(impl: ColorRepositoryImpl): ColorRepository
}