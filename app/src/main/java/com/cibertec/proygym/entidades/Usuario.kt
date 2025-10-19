package com.cibertec.proygym.entidades

// Lo que esta marcado como comentario
// es para una futura integracion.
data class Usuario (
    var dni : String = "",
    var apellido : String = "",
    var nombres : String = "",
    var celular : String = "",
    var sexo : String = "",
    var correo : String = "",
    var clave : String = "",
    //var peso_inicial : String = "",
    var peso : Double,
    var altura : Double,
    //var imcInicial : Double = "",
    //var imc : Double,
)