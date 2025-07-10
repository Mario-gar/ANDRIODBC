package com.example.burningcalories.model

data class RegisterRequest(
    val primerNombre: String,
    val segundoNombre: String,
    val primerApellido: String,
    val segundoApellido: String,
    val edad: Int,
    val tipoPersona: TipoPersona,
    val email: String,
    val contrasena: String
)

