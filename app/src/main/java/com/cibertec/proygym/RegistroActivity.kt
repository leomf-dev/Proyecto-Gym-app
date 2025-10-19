package com.cibertec.proygym

import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cibertec.proygym.data.GymDB
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text

class RegistroActivity : AppCompatActivity() {
    private lateinit var tietDni : TextInputEditText
    private lateinit var tietApellido : TextInputEditText
    private lateinit var tietNombres : TextInputEditText
    private lateinit var tietCelular : TextInputEditText
    private lateinit var rgSexo : RadioGroup
    private lateinit var tietCorreo : TextInputEditText
    private lateinit var tietClave : TextInputEditText
    private lateinit var tietConfirmarClave : TextInputEditText
    private lateinit var tietPeso : TextInputEditText
    private lateinit var tietAltura : TextInputEditText
    private lateinit var btnGuardar : MaterialButton
    val dbHelper = GymDB(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)

        tietDni = findViewById(R.id.tietDni)
        tietApellido = findViewById(R.id.tietApellido)
        tietNombres = findViewById(R.id.tietNombres)
        tietCelular = findViewById(R.id.tietCelular)
        rgSexo = findViewById(R.id.rgSexo)
        tietCorreo = findViewById(R.id.tietCorreo)
        tietClave = findViewById(R.id.tietClave)
        tietConfirmarClave = findViewById(R.id.tietClaveConfirmar)
        tietPeso = findViewById(R.id.tietPeso)
        tietAltura = findViewById(R.id.tietAltura)
        btnGuardar = findViewById(R.id.btnGuardar)

        btnGuardar.setOnClickListener {
            validar()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    fun validar(){
        val dni = tietDni.text.toString().trim()
        if (dni.isEmpty() || dni.length < 8) {
            Toast.makeText(this, "Ingresa tu DNI correctamente.", Toast.LENGTH_SHORT).show()
            return
        }

        val apellido = tietApellido.text.toString().trim()
        if (apellido.isEmpty()) {
            Toast.makeText(this, "Ingresa tu apellido.", Toast.LENGTH_SHORT).show()
            return
        }

        val nombres = tietNombres.text.toString().trim()
        if (nombres.isEmpty()) {
            Toast.makeText(this, "Ingresa tu nombre.", Toast.LENGTH_SHORT).show()
            return
        }

        val peso = tietPeso.text.toString().trim()
        if (peso.isEmpty()) {
            Toast.makeText(this, "Ingresa tu peso.", Toast.LENGTH_SHORT).show()
            return
        }

        val altura = tietAltura.text.toString().trim()
        if (altura.isEmpty()) {
            Toast.makeText(this, "Ingresa tu altura.", Toast.LENGTH_SHORT).show()
            return
        }

        val celular = tietCelular.text.toString().trim()
        if (celular.isEmpty()) {
            Toast.makeText(this, "Ingresa tu celular.", Toast.LENGTH_SHORT).show()
            return
        }
        if (celular.length != 9) {
            Toast.makeText(this, "El teléfono debe tener 9 dígitos.", Toast.LENGTH_SHORT).show()
            return
        }

        val sexoId = rgSexo.checkedRadioButtonId
        if (sexoId == -1) {
            Toast.makeText(this, "Selecciona un sexo.", Toast.LENGTH_SHORT).show()
            return
        }
        val sexo = findViewById<RadioButton>(sexoId).text.toString()

        val correo = tietCorreo.text.toString().trim()
        if (correo.isEmpty()) {
            Toast.makeText(this, "Ingresa tu correo.", Toast.LENGTH_SHORT).show()
            return
        }

        val clave = tietClave.text.toString().trim()
        if (clave.isEmpty()) {
            Toast.makeText(this, "Ingresa tu clave.", Toast.LENGTH_SHORT).show()
            return
        }
        if (clave.length < 8) {
            Toast.makeText(this, "La contraseña debe tener al menos 8 caracteres.", Toast.LENGTH_SHORT).show()
            return
        }

        val confirmarClave = tietConfirmarClave.text.toString().trim()
        if (confirmarClave.isEmpty()) {
            Toast.makeText(this, "Confirma tu clave.", Toast.LENGTH_SHORT).show()
            return
        }
        if (clave != confirmarClave) {
            Toast.makeText(this, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show()
            return
        }

        registrarUsuario(dni, nombres, apellido, celular, sexo, correo, clave, peso.toDouble(), altura.toDouble())
    }


    fun registrarUsuario(dni: String, nombres: String, apellido: String, celular: String, sexo: String, correo: String, clave: String, peso: Double, altura: Double) {
        val db = dbHelper.writableDatabase

        // Verificar si ya existe el correo
        val cursor = db.rawQuery("SELECT correo FROM usuario WHERE correo = ?", arrayOf(correo))
        if (cursor.count > 0) {
            cursor.close()
            db.close()
            Toast.makeText(this, "El correo ya está registrado", Toast.LENGTH_SHORT).show()
            return
        }
        cursor.close()

        // Insertar nuevo usuario
        val values = ContentValues().apply {
            put("dni", dni)
            put("nombres", nombres)
            put("apellido", apellido)
            put("celular", celular)
            put("sexo", sexo)
            put("correo", correo)
            put("clave", clave)
            put("peso", peso)
            put("altura", altura)
            //put("imc", imc)
        }

        val resultado = db.insert("usuario", null, values)
        db.close()

        if (resultado != -1L) {
            Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
            finish() // Cierra la pantalla y regresa al login
        } else {
            Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
        }
    }
}