package com.cibertec.proygym.adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.proygym.R
import com.cibertec.proygym.entidades.PlanNutricional


class PlanNutricionalAdapter(private val plannutris: List<PlanNutricional>) : RecyclerView.Adapter<PlanNutricionalAdapter.PlanNutricionalViewHolder>(){


    inner class PlanNutricionalViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val tvNombre : TextView = view.findViewById(R.id.tvNombre)
        val tvObjetivo : TextView = view.findViewById(R.id.tvObjetivo)
        val tvCaloriasDiarias : TextView = view.findViewById(R.id.tvCaloriasDiarias)
        val tvAsesor : TextView = view.findViewById(R.id.tvAsesor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanNutricionalViewHolder{
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_plan_nutricional, parent, false)
        return PlanNutricionalViewHolder(view)
    }


    override fun onBindViewHolder(holder: PlanNutricionalViewHolder, position: Int) {
        val plannu = plannutris[position]
        holder.tvNombre.text = plannu.nombre
        holder.tvObjetivo.text = "Objetivo: ${plannu.objetivo}"
        holder.tvCaloriasDiarias.text = "Calorias Diarias: ${plannu.caloriasDiarias}"
        holder.tvAsesor.text = "Asesor: ${plannu.asesor}"
    }

    override fun getItemCount(): Int = plannutris.size
}