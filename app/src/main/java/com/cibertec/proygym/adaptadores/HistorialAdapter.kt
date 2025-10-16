package com.cibertec.proygym.adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.proygym.R
import com.cibertec.proygym.entidades.Compra

class HistorialAdapter (var listaCompras : List<Compra>) : RecyclerView.Adapter<HistorialAdapter.HistorialViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistorialViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_historial, parent, false)
        return HistorialViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistorialViewHolder, position: Int) {
        val compra : Compra = listaCompras[position]
        var cantidad = compra.cantidad
        var precio = compra.precio
        var subtotal = cantidad * precio
        holder.tvProducto.text = compra.producto
        holder.tvCantidad.text = "Cantidad: " + compra.cantidad + "\n Subtotal: " + subtotal
        holder.tvFecha.text = "Fecha: " + compra.fecha
    }

    override fun getItemCount(): Int {
        return listaCompras.size
    }

    inner class HistorialViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var tvProducto : TextView = itemView.findViewById(R.id.tvProducto)
        var tvCantidad : TextView = itemView.findViewById(R.id.tvCantidad)
        var tvFecha : TextView = itemView.findViewById(R.id.tvFecha)
    }
}