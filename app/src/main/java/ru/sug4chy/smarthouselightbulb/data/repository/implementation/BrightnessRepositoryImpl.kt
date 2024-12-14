package ru.sug4chy.smarthouselightbulb.data.repository.implementation

import retrofit2.HttpException
import ru.sug4chy.smarthouselightbulb.data.network.BrightnessService
import ru.sug4chy.smarthouselightbulb.data.repository.BrightnessRepository
import ru.sug4chy.smarthouselightbulb.data.repository.mapResponseToResult
import javax.inject.Inject

internal class BrightnessRepositoryImpl @Inject constructor(
    private val brightnessService: BrightnessService
) : BrightnessRepository {

    override suspend fun getCurrentBrightnessLevel(): Result<Int> =
        mapResponseToResult(
            responseProvider = {
                brightnessService.getCurrentBrightnessLevel()
            },
            onSuccess = { response ->
                return if (response.isSuccessful) {
                    Result.success(response.body() ?: 0)
                } else {
                    Result.failure(HttpException(response))
                }
            },
            onFailure = Result.Companion::failure
        )

    override suspend fun setBrightnessLevel(level: Int): Result<Unit> =
        mapResponseToResult(
            responseProvider = {
                brightnessService.setBrightnessLevel(level)
            },
            onSuccess = { response ->
                return if (response.isSuccessful) {
                    Result.success(Unit)
                } else {
                    Result.failure(HttpException(response))
                }
            },
            onFailure = Result.Companion::failure
        )
}