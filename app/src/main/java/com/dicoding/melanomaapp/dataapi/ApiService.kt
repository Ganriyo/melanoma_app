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
}