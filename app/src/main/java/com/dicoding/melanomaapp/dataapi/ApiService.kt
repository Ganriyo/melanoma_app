package com.dicoding.melanomaapp.dataapi

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @POST("v1.0/scan-upload")
    suspend fun uploadScan(
        @Part image: MultipartBody.Part,
        @Part("userId") userId: RequestBody
    ): FileUploadResponse

    companion object {
        private const val BASE_URL = "http://34.101.204.222:5000/"

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}