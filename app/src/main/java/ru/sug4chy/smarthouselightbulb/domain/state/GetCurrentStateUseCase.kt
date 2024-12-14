package ru.sug4chy.smarthouselightbulb.domain.state

import ru.sug4chy.smarthouselightbulb.data.model.LightbulbState

interface GetCurrentStateUseCase {
    suspend operator fun invoke(): Result<LightbulbState>
}