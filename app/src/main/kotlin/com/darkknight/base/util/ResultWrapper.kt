package com.darkknight.base.util

import com.darkknight.base.data.network.ApiError

/**
 *
 * Created by Rooparsh Kalia on 11/04/20
 *
 **/
sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class GenericError(val error: ApiError) :
        ResultWrapper<Nothing>()

    data class NetworkError(val message: String) : ResultWrapper<Nothing>()
}