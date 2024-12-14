package ru.sug4chy.smarthouselightbulb.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface LightbulbStateService {

    @POST("state/on")
    suspend fun turnOn(): Response<Boolean>

    @POST("state/off")
    suspend fun turnOff(): Response<Boolean>

    @GET("state/")
    suspend fun getState(): Response<Boolean>
}