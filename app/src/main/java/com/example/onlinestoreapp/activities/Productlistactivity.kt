package com.example.onlinestoreapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinestoreapp.Fragments.FragmentMenu
import com.example.onlinestoreapp.R

class Productlistactivity : AppCompatActivity() {
 private lateinit var btn_menu : ImageButton
 private lateinit var btnprofile: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.productlist) //
        btn_menu = findViewById(R.id.menu)
        btnprofile = findViewById(R.id.btn_profile)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerProductos)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val listaProductos = listOf(
            Producto("Bandeja de golosinas", "$30.000", R.drawable.img_bandeja_5h),
            Producto("Ancheta madre", "$80.000", R.drawable.img_mamaancheta),
            Producto("Detalle de flores madre", "$60.000", R.drawable.img_madreachampan),
            Producto("Detalle floral lirios madre", "$50.000", R.drawable.img_lirios_madre)
        )

        val adapter = ProductoAdapter(listaProductos)
        recyclerView.adapter = adapter

        btn_menu.setOnClickListener {
            val panel = FragmentMenu()
            panel.show(supportFragmentManager, "FragmentMenu")

        }
        btnprofile.setOnClickListener {
            val intent = Intent(this, ProfileCamActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}