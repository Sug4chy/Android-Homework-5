package ru.sug4chy.smarthouselightbulb.domain.brightness.implementation

import ru.sug4chy.smarthouselightbulb.data.repository.BrightnessRepository
import ru.sug4chy.smarthouselightbulb.domain.brightness.GetCurrentBrightnessUseCase
import javax.inject.Inject

internal class GetCurrentBrightnessUseCaseImpl @Inject constructor(
    private val brightnessRepository: BrightnessRepository
) : GetCurrentBrightnessUseCase {

    override suspend operator fun invoke(): Result<Int> =
        brightnessRepository.getCurrentBrightnessLevel()
}