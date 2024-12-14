package ru.sug4chy.smarthouselightbulb.domain.state.implementation

import ru.sug4chy.smarthouselightbulb.data.model.LightbulbState
import ru.sug4chy.smarthouselightbulb.data.repository.LightbulbStateRepository
import ru.sug4chy.smarthouselightbulb.domain.state.GetCurrentStateUseCase
import javax.inject.Inject

internal class GetCurrentStateUseCaseImpl @Inject constructor(
    private val stateRepository: LightbulbStateRepository
) : GetCurrentStateUseCase {

    override suspend operator fun invoke(): Result<LightbulbState> =
        stateRepository.getState()
}