package com.cibertec.proygym.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.proygym.R
import com.cibertec.proygym.adaptadores.PlanNutricionalAdapter
import com.cibertec.proygym.entidades.PlanNutricional

class InicioFragment : Fragment(R.layout.fragment_inicio){

    private lateinit var rvPlanes : RecyclerView
    //private lateinit var planNutricionalAdapter : PlanNutricionalAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvPlanes = view.findViewById(R.id.rvPlanes)
        // requireContext() = devuelve el Context asociado al fragmento.
        rvPlanes.layoutManager = LinearLayoutManager(requireContext())

        val listaPlanes = listOf(
            PlanNutricional(
                "Plan saludable para principiantes",
                "Introducir buenos habitos sin contar calorias estrictas",
                0,
                "Nunca Quien"
            )
        )

        rvPlanes.adapter = PlanNutricionalAdapter(listaPlanes)

    }
}