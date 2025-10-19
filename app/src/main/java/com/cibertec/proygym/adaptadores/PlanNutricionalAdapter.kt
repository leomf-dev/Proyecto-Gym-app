package com.cibertec.proygym.adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.proygym.R
import com.cibertec.proygym.entidades.PlanNutricional

// private val plannutris = Constructor primario y propieda de la clase "class PlanNutricionalAdapter".

// val (inmutable/read-only) = significa "value" en espanol "valor" y no se puede reasignar/modificar.
// var (mutable) = significa "variable" y se puede reasignar/modificar.

// Lista inmutable (Read-only) { List<T> } = se crean con la funciona listOf(), no se puede agregar, eliminar ni reemplazar elementos.
//      Todos los metodos de acceso estan disponibles (get, size, etc.).
// Lista mutable (Modificable) { MutableList<T> } = Se crea con mutableListOf(), en este si se puede agregar, eliminar, actualizar (con el operador [] o set())
//      y ordenar elementos. Hereda todas las funciones de la lista inmutable.

// RecyclerView.Adapter<T> = Es una clase que actua como puente entre la fuente de datos y el RecyclerView (interfaz de usuario).
// Se requiere que implemente 3 metodos para funcionar:
//      onCreateViewHolder(parent: Viewgroup, viewType: Int) : T
//      onBindViewHolder(Holder: T, position: Int)
//      getItemCount() : Int
class PlanNutricionalAdapter(private val plannutris: List<PlanNutricional>) : RecyclerView.Adapter<PlanNutricionalAdapter.PlanNutricionalViewHolder>(){

    // inner class = Declara a la clase como clase anidada que esta intimamente ligada a su clase contenedora.
    //          Usar inner permite acceder a los miembros de la clase externa (osea lo que estan aqui PlanNutricionalAdapter).
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

    // onBindViewHolder = Se toma el dato de la posicion actual de la lista y se asigna a los Views
    //                  como TextViews o ImageViews, dentro del ViewHolder (que ya fue creado).
    override fun onBindViewHolder(holder: PlanNutricionalViewHolder, position: Int) {
        val plannu = plannutris[position] // aveces me olvido que esto es una lista
        holder.tvNombre.text = plannu.nombre
        holder.tvObjetivo.text = "Objetivo: ${plannu.objetivo}"
        holder.tvCaloriasDiarias.text = "Calorias Diarias: ${plannu.caloriasDiarias}"
        holder.tvAsesor.text = "Asesor: ${plannu.asesor}"
    }

    // getItemCount(): Int = Devuelve el total de numeros de elementos de la lista.
    //              Es para que RecyclerView sepa cuantas filas o elementos va.
    override fun getItemCount(): Int = plannutris.size
}