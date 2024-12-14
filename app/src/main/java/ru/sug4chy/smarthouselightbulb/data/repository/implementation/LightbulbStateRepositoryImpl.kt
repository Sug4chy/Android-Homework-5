package ru.sug4chy.smarthouselightbulb.data.repository.implementation

import retrofit2.HttpException
import ru.sug4chy.smarthouselightbulb.data.model.LightbulbState
import ru.sug4chy.smarthouselightbulb.data.repository.LightbulbStateRepository
import ru.sug4chy.smarthouselightbulb.data.repository.mapResponseToResult
import ru.sug4chy.smarthouselightbulb.data.network.LightbulbStateService
import javax.inject.Inject

internal class LightbulbStateRepositoryImpl @Inject constructor(
    private val lightbulbStateService: LightbulbStateService
) : LightbulbStateRepository {

    override suspend fun getState(): Result<LightbulbState> =
        mapResponseToResult(
            responseProvider = { lightbulbStateService.getState() },
            onSuccess = { response ->
                if (response.isSuccessful) {
                    return Result.success(
                        LightbulbState.fromBoolean(response.body() ?: false)
                    )
                } else {
                    return Result.failure(HttpException(response))
                }
            },
            onFailure = Result.Companion::failure
        )

    override suspend fun setState(state: LightbulbState): Result<Unit> =
        mapResponseToResult(
            responseProvider = {
                when (state) {
                    LightbulbState.ON -> lightbulbStateService.turnOn()
                    LightbulbState.OFF -> lightbulbStateService.turnOff()
                }
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