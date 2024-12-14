package ru.sug4chy.smarthouselightbulb.data.repository

import ru.sug4chy.smarthouselightbulb.data.model.Color

interface ColorRepository {
    suspend fun getAllColors(): Result<List<String>>
    suspend fun getCurrentColor(): Result<Color>
    suspend fun setColor(color: String): Result<Unit>
}