package ru.sug4chy.smarthouselightbulb.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ru.sug4chy.smarthouselightbulb.data.model.Color

interface ColorService {

    @GET("color/names_only")
    suspend fun getAllColors(): Response<List<String>>

    @GET("color/current")
    suspend fun getCurrentColor(): Response<Color>

    @POST("color/")
    suspend fun setColor(
        @Query("color") colorName: String
    ): Response<Boolean>
}