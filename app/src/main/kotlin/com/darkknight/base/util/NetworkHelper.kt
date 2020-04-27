package com.darkknight.base.util

import com.darkknight.base.data.network.ApiError
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 *
 * Created by Rooparsh Kalia on 11/04/20
 *
 **/

suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): ResultWrapper<T> = try {
    apiCall.invoke().resolveResponse()
} catch (throwable: Throwable) {
    when (throwable) {
        is IOException -> ResultWrapper.NetworkError(throwable.message.orEmpty())
        is HttpException -> with(throwable) {
            ResultWrapper.GenericError(ApiError(code(), message()))
        }
        else -> ResultWrapper.NetworkError(throwable.message.orEmpty())
    }
}

private fun <T> Response<T>.resolveResponse(): ResultWrapper<T> =
    if (this.isSuccessful) {
        this.body()?.let {
            ResultWrapper.Success(it)
        } ?: kotlin.run {
            ResultWrapper.GenericError(this.errorBody().toApiError())
        }
    } else {
        ResultWrapper.GenericError(this.errorBody().toApiError())
    }

@OptIn(UnstableDefault::class)
private fun ResponseBody?.toApiError(): ApiError = Json(
    JsonConfiguration(
        ignoreUnknownKeys = true,
        isLenient = true
    )
).parse(
    ApiError.serializer(),
    this?.charStream()?.readText().orEmpty()
)
