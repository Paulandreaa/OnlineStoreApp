package com.example.onlinestoreapp.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.onlinestoreapp.R

class splashactivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT :Long=3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, Loginactivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT)}
    }
