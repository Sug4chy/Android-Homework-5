package ru.sug4chy.smarthouselightbulb.di.provides

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.sug4chy.smarthouselightbulb.BuildConfig
import ru.sug4chy.smarthouselightbulb.data.network.BrightnessService
import ru.sug4chy.smarthouselightbulb.data.network.ColorService
import ru.sug4chy.smarthouselightbulb.data.network.LightbulbStateService

@Module
class NetworkProvidesModule {

    @Provides
    fun provideLightbulbStateService(): LightbulbStateService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LightbulbStateService::class.java)

    @Provides
    fun provideBrightnessService(): BrightnessService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BrightnessService::class.java)

    @Provides
    fun provideColorService(): ColorService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ColorService::class.java)
}