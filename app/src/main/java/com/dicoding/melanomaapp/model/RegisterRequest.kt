package com.dicoding.melanomaapp.model

data class RegisterRequest(
    val email: String,
    val password: String,
    val name: String
)
