package com.dicoding.melanomaapp.view

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dicoding.melanomaapp.R
import com.dicoding.melanomaapp.api.ApiConfig
import com.dicoding.melanomaapp.api.ApiService
import com.dicoding.melanomaapp.api.FileUploadResponse
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream


class ScanActivity : AppCompatActivity() {

    private lateinit var galleryButton: Button
    private lateinit var scanButton: Button
    private lateinit var previewImageView: ImageView
    private val REQUEST_CODE_PICK_IMAGE = 1
    private lateinit var apiService: ApiService
    private val TEST_USER_ID = "Ke9oXiAzCkKf6Wt89GfR"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)
        apiService = ApiConfig.getApiService()

        galleryButton = findViewById(R.id.galleryButton)
        previewImageView = findViewById(R.id.previewImageView)
        scanButton = findViewById(R.id.scanButton)
        val retrofit = Retrofit.Builder()
            .baseUrl("http://34.101.204.222:5000/v1.0/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)


        val btnBack: ImageView = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }

        scanButton.setOnClickListener {
            val imageFile = getImageFileFromImageView()
            val userId = TEST_USER_ID

            if (imageFile != null && userId != null) {
                lifecycleScope.launch {
                    try {
                        val response = uploadScan(imageFile, userId)
                            val confidence = response.confidence ?: ""
                            val explanation = response.explanation ?: ""
                            val suggestion = response.suggestion ?: ""

                            val intent = Intent(this@ScanActivity, ResultActivity::class.java)
                            intent.putExtra("CONFIDENCE", confidence)
                            intent.putExtra("EXPLANATION", explanation)
                            intent.putExtra("SUGGESTION", suggestion)
                            startActivity(intent)
                    } catch (e: Exception) {
                        Toast.makeText(this@ScanActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                val message = if (imageFile == null) "Pilih gambar terlebih dahulu" else "User ID tidak ditemukan"
                Toast.makeText(this@ScanActivity, message, Toast.LENGTH_SHORT).show()
            }
        }

        galleryButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE)
        }

    }

    private suspend fun uploadScan(imageFile: File, userId: String): FileUploadResponse {
        val requestBody = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
        val imagePart = MultipartBody.Part.createFormData("image", imageFile.name, requestBody)
        val userIdPart = userId.toRequestBody("text/plain".toMediaTypeOrNull())

        return apiService.uploadScan(imagePart, userIdPart)
    }

    private fun getImageFileFromImageView(): File? {
        val drawable = previewImageView.drawable as? BitmapDrawable
        if (drawable != null) {
            val bitmap = drawable.bitmap
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos)
            val byteArray = bos.toByteArray()

            val file = File(cacheDir, "temp_image.jpg")
            try {
                val fos = FileOutputStream(file)
                fos.write(byteArray)
                fos.flush()
                fos.close()
                return file
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data
            previewImageView.setImageURI(selectedImageUri)
        }
    }

}