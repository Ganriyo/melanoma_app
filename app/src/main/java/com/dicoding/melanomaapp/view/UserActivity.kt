package com.dicoding.melanomaapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.melanomaapp.R

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val homeButton = findViewById<ImageView>(R.id.homeButton)
        val historyButton = findViewById<ImageView>(R.id.historyButton)
        val profileButton = findViewById<ImageView>(R.id.profileButton)

        homeButton.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        historyButton.setOnClickListener{
            val intent = Intent(this,HistoryActivity::class.java)
            startActivity(intent)
        }

        profileButton.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }

    }
}