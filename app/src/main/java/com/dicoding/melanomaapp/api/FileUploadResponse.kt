package com.dicoding.melanomaapp.api

import com.google.gson.annotations.SerializedName

data class FileUploadResponse (
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("userId")
    var userId: String? = null,
    @SerializedName("fileName")
    var fileName: String? = null,
    @SerializedName("uploadAt")
    var uploadAt: String? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("confidence")
    var confidence: String? = null,
    @SerializedName("explanation")
    var explanation: String? = null,
    @SerializedName("suggestion")
    var suggestion: String? = null,
)