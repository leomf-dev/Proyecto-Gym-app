package com.cibertec.proygym.entidades

// Comentado = Futura integracion.
// Aqui van las Comidas (otra entidad, FK), pero por ahora sera solo Plan Nutricional.
data class PlanNutricional (
    var nombre : String = "",
    var objetivo : String = "",
    var caloriasDiarias : Int,
    var asesor : String = "",
    // var fechaInicio : date,
    // var fechaFin : date
)