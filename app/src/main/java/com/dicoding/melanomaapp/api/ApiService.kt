package com.dicoding.melanomaapp.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @POST("v1.1/scan-upload")
    suspend fun uploadScan(
        @Part image: MultipartBody.Part,
        @Part("userId") userId: RequestBody
    ): FileUploadResponse
}