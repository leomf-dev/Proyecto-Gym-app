package com.cibertec.proygym.entidades


data class Usuario (
    var dni : String = "",
    var apellido : String = "",
    var nombres : String = "",
    var celular : String = "",
    var sexo : String = "",
    var correo : String = "",
    var clave : String = "",

    var peso : Double,
    var altura : Double,

)