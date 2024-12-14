package ru.sug4chy.smarthouselightbulb.data.repository.implementation

import retrofit2.HttpException
import ru.sug4chy.smarthouselightbulb.data.model.Color
import ru.sug4chy.smarthouselightbulb.data.network.ColorService
import ru.sug4chy.smarthouselightbulb.data.repository.ColorRepository
import ru.sug4chy.smarthouselightbulb.data.repository.mapResponseToResult
import javax.inject.Inject

internal class ColorRepositoryImpl @Inject constructor(
    private val colorService: ColorService
) : ColorRepository {

    override suspend fun getAllColors(): Result<List<String>> =
        mapResponseToResult(
            responseProvider = {
                colorService.getAllColors()
            },
            onSuccess = { response ->
                return if (response.isSuccessful) {
                    Result.success(response.body() ?: emptyList())
                } else {
                    Result.failure(HttpException(response))
                }
            },
            onFailure = Result.Companion::failure
        )

    override suspend fun getCurrentColor(): Result<Color> =
        mapResponseToResult(
            responseProvider = {
                colorService.getCurrentColor()
            },
            onSuccess = { response ->
                return if (response.isSuccessful) {
                    Result.success(
                        response.body()
                            ?: return Result.failure(IllegalStateException("Current color is null"))
                    )
                } else {
                    Result.failure(HttpException(response))
                }
            },
            onFailure = Result.Companion::failure
        )

    override suspend fun setColor(color: String): Result<Unit> =
        mapResponseToResult(
            responseProvider = {
                colorService.setColor(color)
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