package ru.sug4chy.smarthouselightbulb.data.repository

interface BrightnessRepository {
    suspend fun getCurrentBrightnessLevel(): Result<Int>
    suspend fun setBrightnessLevel(level: Int): Result<Unit>
}