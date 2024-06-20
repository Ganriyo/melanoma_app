package com.dicoding.melanomaapp.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.dicoding.melanomaapp.R

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val scanButton: CardView = findViewById(R.id.scanButton)
        val homeButton = findViewById<ImageView>(R.id.homeButton)
        val historyButton = findViewById<ImageView>(R.id.historyButton)
        val profileButton = findViewById<ImageView>(R.id.profileButton)

        val applicationInfo = getApplicationContext().applicationInfo

        scanButton.setOnClickListener {
            val intent = Intent(this, ScanActivity::class.java)
            startActivity(intent)
        }

        homeButton.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        historyButton.setOnClickListener{
            val intent = Intent(this,HistoryActivity::class.java)
            startActivity(intent)
        }

        profileButton.setOnClickListener {
            // Intent untuk berpindah ke UserActivity
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }
    }
}