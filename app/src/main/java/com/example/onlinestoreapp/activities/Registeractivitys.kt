package com.example.onlinestoreapp.activities
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.db.Sqlitedatabase

class Registeractivitys : AppCompatActivity() {

    private lateinit var etUsuario: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etContrasena: EditText
    private lateinit var etConfirmacion: EditText
    private lateinit var btnRegistrar: ImageButton
    private lateinit var db: Sqlitedatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        db = Sqlitedatabase(this)

        etUsuario = findViewById(R.id.Username)
        etCorreo = findViewById(R.id.mail)
        etContrasena = findViewById(R.id.createpassword)
        etConfirmacion = findViewById(R.id.confirmpassword)
        btnRegistrar = findViewById(R.id.btn_next_login)

        btnRegistrar.setOnClickListener {
            val usuario = etUsuario.text.toString().trim()
            val correo = etCorreo.text.toString().trim()
            val contrasena = etContrasena.text.toString().trim()
            val confirmacion = etConfirmacion.text.toString().trim()

            if (usuario.isEmpty() || correo.isEmpty() || contrasena.isEmpty() || confirmacion.isEmpty()) {
                Toast.makeText(this, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (contrasena != confirmacion) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val dbWritable = db.writableDatabase
            val valores = ContentValues().apply {
                put("usuario", usuario)
                put("correo", correo)
                put("contrasena", contrasena)
                put("confirmacion_contrasena", confirmacion)
            }

            val resultado = dbWritable.insert("usuarios", null, valores)
            if (resultado != -1L) {
                Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Loginactivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
