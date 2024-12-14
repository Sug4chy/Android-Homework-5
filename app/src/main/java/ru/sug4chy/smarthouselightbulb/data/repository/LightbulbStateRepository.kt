package ru.sug4chy.smarthouselightbulb.data.repository

import ru.sug4chy.smarthouselightbulb.data.model.LightbulbState

interface LightbulbStateRepository {
    suspend fun getState(): Result<LightbulbState>
    suspend fun setState(state: LightbulbState): Result<Unit>
}