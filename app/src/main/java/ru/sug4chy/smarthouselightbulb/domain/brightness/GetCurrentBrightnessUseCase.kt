package ru.sug4chy.smarthouselightbulb.domain.brightness

interface GetCurrentBrightnessUseCase {
    suspend operator fun invoke(): Result<Int>
}