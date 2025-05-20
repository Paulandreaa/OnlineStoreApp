package com.example.onlinestoreapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.onlinestoreapp.activities.Productlistactivity
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.db.Sqlitedatabase
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class Loginactivity : AppCompatActivity() {

    // login con SQLite
    private lateinit var db: Sqlitedatabase //nombre de la base de datos
    private lateinit var etUsuario: EditText //usuario
    private lateinit var etContrasena: EditText //contraseña
    private lateinit var btnLogin: ImageButton //boton flecha

    // login con Google
    private lateinit var btngooglesesion: ImageButton //boton google sesion
    private lateinit var googleSignInClient: GoogleSignInClient //variable inicio
    private val RC_SIGN_IN = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // sqlite login
        db = Sqlitedatabase(this)
        etUsuario = findViewById(R.id.Username)
        etContrasena = findViewById(R.id.password)
        btnLogin = findViewById(R.id.btn_next_listproduct)

        btnLogin.setOnClickListener {
            val usuario = etUsuario.text.toString()
            val contrasena = etContrasena.text.toString()

            if (db.validarUsuario(usuario, contrasena)) {
                val intent = Intent(this, Productlistactivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Incorrecto, por favor verifique su nombre de usuario o contraseña", Toast.LENGTH_SHORT).show()
            }
        }

        // Login google
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        btngooglesesion = findViewById(R.id.btn_googlesesion)
        btngooglesesion.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)

            // ingreso valido
            Log.d("GOOGLE_SIGN_IN", "Cuenta: ${account.email}")

            val intent = Intent(this, Productlistactivity::class.java)
            startActivity(intent)
            finish()

        } catch (e: ApiException) {
            Log.w("GOOGLE_SIGN_IN", "Error en login con Google. Código: ${e.statusCode}")
            Toast.makeText(this, "Error al iniciar sesión con Google", Toast.LENGTH_SHORT).show()
        }
    }
}

