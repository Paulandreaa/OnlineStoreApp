package com.example.onlinestoreapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.DialogFragment
import com.example.onlinestoreapp.R

class ProfileCamActivity: AppCompatActivity() {
    private lateinit var btnregresohome: ImageButton
    private lateinit var btntakephoto: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.frg_profile)
        btnregresohome = findViewById(R.id.return_home)
        btnregresohome.setOnClickListener {
            val intent = Intent(this, Productlistactivity::class.java)
            startActivity(intent)
            finish()
        }
        btntakephoto = findViewById(R.id.getphoto)
        btntakephoto.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
