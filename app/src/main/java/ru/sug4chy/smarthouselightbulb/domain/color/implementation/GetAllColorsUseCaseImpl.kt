package ru.sug4chy.smarthouselightbulb.domain.color.implementation

import ru.sug4chy.smarthouselightbulb.data.repository.ColorRepository
import ru.sug4chy.smarthouselightbulb.domain.color.GetAllColorsUseCase
import javax.inject.Inject

internal class GetAllColorsUseCaseImpl @Inject constructor(
    private val colorRepository: ColorRepository
) : GetAllColorsUseCase {

    override suspend operator fun invoke(): Result<List<String>> =
        colorRepository.getAllColors()
}