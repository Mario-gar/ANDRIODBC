package com.example.burningcalories.model

data class RegisterRequest(
    val email: String,
    val contrasena: String,
    val primerNombre: String,
    val segundoNombre: String,
    val primerApellido: String,
    val segundoApellido: String,
    val edad: Int,
    val tipoPersona: TipoPersona
)
