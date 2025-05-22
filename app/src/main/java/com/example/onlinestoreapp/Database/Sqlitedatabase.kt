package com.example.onlinestoreapp.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Sqlitedatabase(context: Context) :
    SQLiteOpenHelper(context, "usuarios.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE usuarios(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "usuario TEXT," +
                    "contrasena TEXT," +
                    "confirmacioncontrasena TEXT," +
                    "correo TEXT)"

        )
// Insertar un usuario de login
        val valores = ContentValues().apply {
            put("usuario", "admin")
            put("contrasena", "1234")
        }
        db.insert("usuarios", null, valores)

        // Insertar un usuario de prueba register
        val valoresregister = ContentValues().apply {
            put("usuario", "admin")
            put("contrasena", "1234")
            put("confirmacionContrasena", "1234")
            put("correo", "admin@correo.com")
        }
        db.insert("usuarios", null, valoresregister)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        onCreate(db)
    }

    fun validarUsuario(usuario: String, contrasena: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM usuarios WHERE usuario = ? AND contrasena = ?"
        val cursor = db.rawQuery(query, arrayOf(usuario, contrasena))
        val existe = cursor.moveToFirst()
        cursor.close()
        db.close()
        return existe
    }
    fun registrarUsuario(usuario: String, contrasena: String, confirmacion: String, correo: String): Boolean {
        if (contrasena != confirmacion) return false

        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("usuario", usuario)
            put("contrasena", contrasena)
            put("confirmacionContrasena", confirmacion)
            put("correo", correo)
        }

        val resultado = db.insert("usuarios", null, values)
        db.close()

        return resultado != -1L
    }
}
