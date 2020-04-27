package com.darkknight.base.data.network

import com.darkknight.base.data.domain.UserData
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

/**
 *
 * Created by Rooparsh Kalia on 2020-02-23
 *
 **/
interface ApiInterface {
    @POST("api/v1/shopOwner/signUp")
    @Headers("Content-type: application/json")
    suspend fun registerShopOwner(@Body map: HashMap<String, String>): Response<CommonResponse<UserData>>

    @POST("api/v1/shopOwner/login")
    @Headers("Content-type: application/json")
    suspend fun loginShopOwner(@Body map: HashMap<String, String>): Response<CommonResponse<UserData>>
}