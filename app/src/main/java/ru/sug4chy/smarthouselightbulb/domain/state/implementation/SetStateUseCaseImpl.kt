package ru.sug4chy.smarthouselightbulb.domain.state.implementation

import ru.sug4chy.smarthouselightbulb.data.model.LightbulbState
import ru.sug4chy.smarthouselightbulb.data.repository.LightbulbStateRepository
import ru.sug4chy.smarthouselightbulb.domain.state.SetStateUseCase
import javax.inject.Inject

internal class SetStateUseCaseImpl @Inject constructor(
    private val stateRepository: LightbulbStateRepository
) : SetStateUseCase {

    override suspend operator fun invoke(state: LightbulbState): Result<Unit> =
        stateRepository.setState(state)
}