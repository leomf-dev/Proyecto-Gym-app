package com.cibertec.proygym

import android.app.Activity
import android.content.Intent
import android.os.Bundle
<<<<<<< HEAD
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
=======
import android.widget.TextView
>>>>>>> 2cadea1e054fc78356d006e0c32a347a3eaa836b
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cibertec.proygym.entidades.Usuario
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AccesoActivity : AppCompatActivity() {
<<<<<<< HEAD
    var tvRegistro: TextView?= null
    private lateinit var tietCorreo : TextInputEditText
    private lateinit var tietClave : TextInputEditText
    private lateinit var tilCorreo : TextInputLayout
    private lateinit var tilClave : TextInputLayout
    private lateinit var btnAcceso : Button

    // Usuarios temporales
    private val listaUsuarios = mutableListOf(
        Usuario(1, "Leo", "Melendez Falcon", "leomf@gmail.com", "1234")
    )
=======

var tvRegistro : TextView ?= null
>>>>>>> 2cadea1e054fc78356d006e0c32a347a3eaa836b

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_acceso)

        tvRegistro = findViewById(R.id.tvRegistro)
<<<<<<< HEAD
        tietCorreo = findViewById(R.id.tietCorreo)
        tietClave = findViewById(R.id.tietClave)
        tilCorreo = findViewById(R.id.tilCorreo)
        tilClave = findViewById(R.id.tilClave)
        btnAcceso = findViewById(R.id.btnInicio)

        btnAcceso.setOnClickListener{
            validarCampos()
        }

        tvRegistro?.setOnClickListener {
=======

        tvRegistro.setOnClickListener {
>>>>>>> 2cadea1e054fc78356d006e0c32a347a3eaa836b
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

<<<<<<< HEAD
    fun validarCampos(){
        val correo = tietCorreo.text.toString().trim()
        val clave = tietClave.text.toString().trim()
        var error:Boolean = false
        if (correo.isEmpty()){
            tilCorreo.error = "Ingrese un correo"
            error = true
        } else {
            tilCorreo.error = ""
        }
        if(clave.isEmpty()){
            tilCorreo.error = "Ingrese contraseña"
            error = true
        } else {
            tilClave.error = ""
        }

        if (error){
            return
        } else {
            Toast.makeText(this, "Validacion correcta, Procesando login...", Toast.LENGTH_LONG).show()

            var usuarioEncontrado:Usuario ?= null

            for (u in listaUsuarios){
                if (u.correo == correo + "@cibertec.edu.pe" && u.clave == clave){
                    usuarioEncontrado = u
                    break
                }
            }
            if(usuarioEncontrado != null){
                Toast.makeText(this, "Bienvenido ${usuarioEncontrado.nombres}", Toast.LENGTH_LONG).show()
                //startActivity(Intent(this, ListaComprasActivity::class.java))
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
            }
        }
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

=======
    fun cambioActivity(activityDestino : Class<out Activity>) {
        val intent = Intent(this, activityDestino)
        startActivity(intent)
    }
>>>>>>> 2cadea1e054fc78356d006e0c32a347a3eaa836b
}