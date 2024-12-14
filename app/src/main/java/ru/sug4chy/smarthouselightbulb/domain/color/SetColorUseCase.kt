package ru.sug4chy.smarthouselightbulb.domain.color

interface SetColorUseCase {
    suspend operator fun invoke(color: String): Result<Unit>
}