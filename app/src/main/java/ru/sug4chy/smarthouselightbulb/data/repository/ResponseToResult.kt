package ru.sug4chy.smarthouselightbulb.data.repository

import retrofit2.Response

inline fun <T, R> mapResponseToResult(
    responseProvider: () -> Response<T>,
    onSuccess: (Response<T>) -> Result<R>,
    onFailure: (Throwable) -> Result<R>
): Result<R> =
    runCatching(responseProvider)
        .fold(
            onSuccess = onSuccess,
            onFailure = onFailure
        )