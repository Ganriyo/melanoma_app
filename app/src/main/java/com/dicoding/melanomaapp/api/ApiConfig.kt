package com.dicoding.melanomaapp.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {

    private val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://34.101.50.114:5000/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(getClient())
        .build()

    private val authRetrofit = Retrofit.Builder()
        .baseUrl("http://34.101.118.58:5000/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(getClient())
        .build()

    fun getApiService(): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    fun getAuthApiService(): ApiService {
        return authRetrofit.create(ApiService::class.java)
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
}
