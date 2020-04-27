package com.darkknight.base.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 * Created by Rooparsh Kalia on 11/04/20
 *
 **/

@Serializable
data class CommonResponse<T>(
    @SerialName("message") val message: String,
    @SerialName("data") val data: T
) {
    override fun toString(): String {
        return "$message\n$data"
    }
}

@Serializable
data class ApiError(
    @SerialName("statusCode") val statusCode: Int = -1,
    @SerialName("message") val message: String
)