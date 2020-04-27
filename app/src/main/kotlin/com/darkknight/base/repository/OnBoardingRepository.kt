package com.darkknight.base.repository

import com.darkknight.base.data.domain.UserData
import com.darkknight.base.data.network.ApiInterface
import com.darkknight.base.data.network.CommonResponse
import com.darkknight.base.util.CommonParams
import com.darkknight.base.util.ResultWrapper
import com.darkknight.base.util.safeApiCall
import com.darkknight.base.util.splitName

/**
 *
 * Created by Rooparsh Kalia on 11/04/20
 *
 **/
class OnBoardingRepositoryImpl(private val apiInterface: ApiInterface) : OnBoardingRepository {

    override suspend fun registerUser(
        name: String,
        email: String,
        phone: String,
        password: String
    ): ResultWrapper<CommonResponse<UserData>> {

        val (firstName, lastName) = name.splitName()

        val params = CommonParams.Builder()
            .add("email", email)
            .add("password", password)
            .add("firstName", firstName)
            .add("lastName", lastName)
            .add("phoneNumber", phone)
            .build()

        return safeApiCall { apiInterface.registerShopOwner(params.map) }

    }

    override suspend fun loginUser(
        email: String,
        password: String
    ): ResultWrapper<CommonResponse<UserData>> {

        val params = CommonParams.Builder()
            .add("email", email)
            .add("password", password)
            .build()

        return safeApiCall { apiInterface.loginShopOwner(params.map) }

    }

}


interface OnBoardingRepository {
    suspend fun registerUser(
        name: String,
        email: String,
        phone: String,
        password: String
    ): ResultWrapper<CommonResponse<UserData>>

    suspend fun loginUser(email: String, password: String): ResultWrapper<CommonResponse<UserData>>
}