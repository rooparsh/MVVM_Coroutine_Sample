package com.darkknight.base.di

import com.darkknight.base.BuildConfig
import com.darkknight.base.data.network.ApiInterface
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 *
 * Created by Rooparsh Kalia on 2020-02-23
 *
 **/

val networkModule = module {

    single { createOkHttpClient() }
    single { createJsonConfiguration() }
    single { createRestAdapter(get(), get()) }
    single { createWebService<ApiInterface>(get()) }

}

fun createOkHttpClient(): OkHttpClient {

    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

fun createJsonConfiguration() = Json(
    JsonConfiguration(
        ignoreUnknownKeys = true,
        isLenient = true
    )
)

fun createRestAdapter(json: Json, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .client(okHttpClient)
    .build()

inline fun <reified T> createWebService(retrofit: Retrofit): T = retrofit.create(T::class.java)