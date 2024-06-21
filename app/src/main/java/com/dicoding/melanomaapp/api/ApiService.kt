package com.dicoding.melanomaapp.api

import com.dicoding.melanomaapp.model.LoginRequest
import com.dicoding.melanomaapp.model.LoginResponse
import com.dicoding.melanomaapp.model.RegisterRequest
import com.dicoding.melanomaapp.model.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @POST("v1.1/scan-upload")
    suspend fun uploadScan(
        @Part file: MultipartBody.Part,
        @Part("userId") userId: RequestBody
    ): FileUploadResponse

    @POST("login")
    fun login(
        @Body request: LoginRequest
    ): Call<LoginResponse>

    @POST("register")
    fun register(
        @Body request: RegisterRequest
    ): Call<RegisterResponse>
}
