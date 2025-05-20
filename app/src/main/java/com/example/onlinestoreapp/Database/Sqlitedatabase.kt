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
                    "contrasena TEXT)"
        )
        // Insertar un usuario de prueba
        val valores = ContentValues().apply {
            put("usuario", "admin")
            put("contrasena", "1234")
        }
        db.insert("usuarios", null, valores)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        onCreate(db)
    }

    fun validarUsuario(usuario: String, contrasena: String): Boolean {
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery(
            "SELECT * FROM usuarios WHERE usuario = ? AND contrasena = ?",
            arrayOf(usuario, contrasena)
        )
        val existe = cursor.count > 0
        cursor.close()
        return existe
    }
}
