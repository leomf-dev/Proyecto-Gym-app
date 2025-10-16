package com.cibertec.proygym

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.proygym.adaptadores.HistorialAdapter
import com.cibertec.proygym.entidades.Compra

class HistorialActivity : AppCompatActivity() {
    private lateinit var rvHistorial : RecyclerView
    private lateinit var historialAdapter : HistorialAdapter

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_historial)

        rvHistorial = findViewById(R.id.rvCompras)
        rvHistorial.layoutManager = LinearLayoutManager(this)
        //rvHistorial.layoutManager = GridLayout(this, 2)
        val listaCompras = listOf(
            Compra("Pan", 20, 2, "07/02/2025"),
            Compra("Pan con queso", 35, 3, "07/02/2025"),
        )
        historialAdapter = HistorialAdapter(listaCompras)
        rvHistorial.adapter = historialAdapter

    }
}