package ru.sug4chy.smarthouselightbulb.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BrightnessService {

    @POST("brightness/")
    suspend fun setBrightnessLevel(
        @Query("level") level: Int
    ): Response<Boolean>

    @GET("brightness/current")
    suspend fun getCurrentBrightnessLevel(): Response<Int>
}