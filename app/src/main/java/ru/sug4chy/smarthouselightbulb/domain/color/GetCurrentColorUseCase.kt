package ru.sug4chy.smarthouselightbulb.domain.color

import ru.sug4chy.smarthouselightbulb.data.model.Color

interface GetCurrentColorUseCase {
    suspend operator fun invoke(): Result<Color>
}