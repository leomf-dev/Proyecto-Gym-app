package com.cibertec.proygym

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cibertec.proygym.data.GymDB
import com.cibertec.proygym.entidades.Usuario
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AccesoActivity : AppCompatActivity() {
    var tvRegistro: TextView?= null
    private lateinit var tietCorreo : TextInputEditText
    private lateinit var tietClave : TextInputEditText
    private lateinit var tilCorreo : TextInputLayout
    private lateinit var tilClave : TextInputLayout
    private lateinit var btnAcceso : Button
    val dbHelper = GymDB(this)

    // Usuarios temporales
    private val listaUsuarios = mutableListOf(
        Usuario("999999999", "Melendez", "Leonel", "987654321", "Masculino",
            "leomf@gmail.com", "leomf@gmail.com", 60.0, 1.70)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_acceso)

        tvRegistro = findViewById(R.id.tvRegistro)
        tietCorreo = findViewById(R.id.tietCorreo)
        tietClave = findViewById(R.id.tietClave)
        tilCorreo = findViewById(R.id.tilCorreo)
        tilClave = findViewById(R.id.tilClave)
        btnAcceso = findViewById(R.id.btnInicio)

        btnAcceso.setOnClickListener{
            validarCampos()
        }

        tvRegistro?.setOnClickListener {
            cambioActivity(RegistroActivity::class.java)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime())
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                maxOf(systemBars.bottom, imeInsets.bottom)
            )
            insets
        }
    }

    fun validarCampos(){
        val correo = tietCorreo.text.toString().trim()
        val clave = tietClave.text.toString().trim()
        var error:Boolean = false
        if (correo.isEmpty()){
            tilCorreo.error = "Ingrese tu correo"
            error = true
        } else {
            tilCorreo.error = ""
        }
        if(clave.isEmpty()){
            tilClave.error = "Ingresa tu contraseña"
            error = true
        } else {
            tilClave.error = ""
        }

        // Buscar usuario en la bd
        // Se crea una instancia de la clase GymBD y this hace referencia al context actual (Activity)
        /* val dbHelper = GymDB(this) */
        // readableDatabase = consultar datos pero no modificar.
        // writableDatabase = insertar, actualizar o eliminar datos.
        val db = dbHelper.readableDatabase
        // rawQuery = consulta SQL de 3 columnas (clave, nombres, celular) de la tabla usuario.
        //          que coincida con el parametro pasado (?).
        // arrawOf(correo) = remplaza el signo (?) por el valor de la variable correo.
        val cursor = db.rawQuery(
            "select clave, nombres, celular from usuario where correo = ?",
            arrayOf(correo)
        )
        // .count = devuelve cuantas filas hubo en la consulta.
        if (cursor.count == 0){
            // .close = nomas pa cerrar la base de datos y liberar recursos.
            cursor.close()
            db.close()
            Toast.makeText(this, "La cuenta no existe", Toast.LENGTH_SHORT).show()
            tietCorreo.setText("")
            tietClave.setText("")
            return
        }

        // Obtiener datos del user
        // Mueve el cursor al primer registro del resultado de la consulta.
        // Cursor = (No es una fila sino...) Es la lista de filas que ha devuelto el SELECT.
        cursor.moveToFirst()
        // cursor.getString = Lee los valores de las columnas del registro.
        // correspondientes al orden en el que se hizo el SELECT (muy importante).
        val claveGuardada = cursor.getString(0)
        val nombres = cursor.getString(1)
        val celular = cursor.getString(2)
        cursor.close()
        db.close()
        // Validar clave
        if(clave != claveGuardada){
            Toast.makeText(this, "Contrasena incorrecta", Toast.LENGTH_SHORT).show()
            tietClave.setText("")
            return
        }

        // Inicia sesion
        val intent = Intent(this, InicioActivity::class.java).apply {
            putExtra("nombres", nombres)
            putExtra("correo", correo)
            putExtra("celular", celular)
            putExtra("clave", clave)
        }
        startActivity(intent)
        finish()

//        if (error){
//            return
//        } else {
//            Toast.makeText(this, "Validacion correcta, Procesando login...", Toast.LENGTH_LONG).show()
//
//            var usuarioEncontrado:Usuario ?= null
//
//            for (u in listaUsuarios){
//                if (u.correo == correo + "@gmail" && u.clave == clave){
//                    usuarioEncontrado = u
//                    break
//                }
//            }
//            if(usuarioEncontrado != null){
//                Toast.makeText(this, "Bienvenido ${usuarioEncontrado.nombres}", Toast.LENGTH_LONG).show()
//                startActivity(Intent(this, InicioActivity::class.java))
//            } else {
//                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
//            }
//        }
    }

    fun cambioActivity(activityDestino : Class<out Activity>){
        val intent = Intent(this, activityDestino)
        startActivity(intent)
    }

    fun abrirVentanaNavegador(){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData("http://www.google.com".toUri())
        startActivity(intent)
    }

    fun abrirBuscadorWeb(){
        val intent = Intent(Intent.ACTION_WEB_SEARCH)
        intent.setData("http://www.google.com".toUri())
        startActivity(intent)
    }
}