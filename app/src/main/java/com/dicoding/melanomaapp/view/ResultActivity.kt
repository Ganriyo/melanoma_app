package com.dicoding.melanomaapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.melanomaapp.R

class ResultActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val confidence = intent.getStringExtra("CONFIDENCE") ?: ""
        val explanation = intent.getStringExtra("EXPLANATION") ?: ""
        val suggestion = intent.getStringExtra("SUGGESTION") ?: ""
        val imageUrl = intent.getStringExtra("IMAGE_URL") ?: ""

        val imageViewResult: ImageView = findViewById(R.id.previewImageView)
        val confidenceTextView: TextView = findViewById(R.id.confidence_text)
        val explanationTextView: TextView = findViewById(R.id.explanation_text)
        val suggestionTextView: TextView = findViewById(R.id.suggestion_text)

        confidenceTextView.text = "Confidence: $confidence"
        explanationTextView.text = "Explanation: $explanation"
        suggestionTextView.text = "Suggestion: $suggestion"

        Glide.with(this)
            .load(imageUrl)
            .into(imageViewResult)

    }
}