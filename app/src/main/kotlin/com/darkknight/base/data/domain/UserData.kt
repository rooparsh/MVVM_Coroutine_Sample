package com.darkknight.base.data.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 * Created by Rooparsh Kalia on 12/04/20
 *
 **/

@Serializable
data class UserData(
    @SerialName("imageURL") val imageUrl: String,
    @SerialName("firstName") val firstName: String,
    @SerialName("lastName") val lastName: String,
    @SerialName("phoneNumber") val phoneNumber: String,
    @SerialName("_id") val id: String
)