package ru.sug4chy.smarthouselightbulb.domain.state

import ru.sug4chy.smarthouselightbulb.data.model.LightbulbState

interface SetStateUseCase {
    suspend operator fun invoke(state: LightbulbState): Result<Unit>
}