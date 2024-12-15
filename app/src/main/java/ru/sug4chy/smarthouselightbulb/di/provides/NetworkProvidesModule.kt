package ru.sug4chy.smarthouselightbulb.di.provides

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.sug4chy.smarthouselightbulb.BuildConfig
import ru.sug4chy.smarthouselightbulb.data.network.BrightnessService
import ru.sug4chy.smarthouselightbulb.data.network.ColorService
import ru.sug4chy.smarthouselightbulb.data.network.LightbulbStateService
import ru.sug4chy.smarthouselightbulb.di.AppComponentScope

@Module
class NetworkProvidesModule {

    @Provides
    @AppComponentScope
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @AppComponentScope
    fun provideLightbulbStateService(retrofit: Retrofit): LightbulbStateService =
        retrofit
            .create(LightbulbStateService::class.java)

    @Provides
    @AppComponentScope
    fun provideBrightnessService(retrofit: Retrofit): BrightnessService =
        retrofit
            .create(BrightnessService::class.java)

    @Provides
    @AppComponentScope
    fun provideColorService(retrofit: Retrofit): ColorService =
        retrofit
            .create(ColorService::class.java)
}