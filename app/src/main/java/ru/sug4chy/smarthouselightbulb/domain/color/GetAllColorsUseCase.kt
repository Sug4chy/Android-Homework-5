package ru.sug4chy.smarthouselightbulb.domain.color

interface GetAllColorsUseCase {
    suspend operator fun invoke(): Result<List<String>>
}