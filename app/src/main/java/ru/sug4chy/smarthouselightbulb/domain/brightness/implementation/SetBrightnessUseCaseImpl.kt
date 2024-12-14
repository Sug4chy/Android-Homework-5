package ru.sug4chy.smarthouselightbulb.domain.brightness.implementation

import ru.sug4chy.smarthouselightbulb.data.repository.BrightnessRepository
import ru.sug4chy.smarthouselightbulb.domain.brightness.SetBrightnessUseCase
import javax.inject.Inject

internal class SetBrightnessUseCaseImpl @Inject constructor(
    private val brightnessRepository: BrightnessRepository
) : SetBrightnessUseCase {

    override suspend operator fun invoke(level: Int): Result<Unit> =
        brightnessRepository.setBrightnessLevel(level)
}