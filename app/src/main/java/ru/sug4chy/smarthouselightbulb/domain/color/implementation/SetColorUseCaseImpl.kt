package ru.sug4chy.smarthouselightbulb.domain.color.implementation

import ru.sug4chy.smarthouselightbulb.data.repository.ColorRepository
import ru.sug4chy.smarthouselightbulb.domain.color.SetColorUseCase
import javax.inject.Inject

internal class SetColorUseCaseImpl @Inject constructor(
    private val colorRepository: ColorRepository
) : SetColorUseCase {

    override suspend operator fun invoke(color: String): Result<Unit> =
        colorRepository.setColor(color)
}