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


        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery(
            "select clave, nombres, celular from usuario where correo = ?",
            arrayOf(correo)
        )

        if (cursor.count == 0){

            cursor.close()
            db.close()
            Toast.makeText(this, "La cuenta no existe", Toast.LENGTH_SHORT).show()
            tietCorreo.setText("")
            tietClave.setText("")
            return
        }


        cursor.moveToFirst()

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