package ru.sug4chy.smarthouselightbulb.domain.brightness

interface SetBrightnessUseCase {
    suspend operator fun invoke(level: Int): Result<Unit>
}