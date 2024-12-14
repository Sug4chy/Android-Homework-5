package ru.sug4chy.smarthouselightbulb.domain.color.implementation

import ru.sug4chy.smarthouselightbulb.data.model.Color
import ru.sug4chy.smarthouselightbulb.data.repository.ColorRepository
import ru.sug4chy.smarthouselightbulb.domain.color.GetCurrentColorUseCase
import javax.inject.Inject

internal class GetCurrentColorUseCaseImpl @Inject constructor(
    private val colorRepository: ColorRepository
) : GetCurrentColorUseCase {

    override suspend operator fun invoke(): Result<Color> =
        colorRepository.getCurrentColor()
}