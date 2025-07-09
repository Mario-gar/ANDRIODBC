package com.example.burningcalories.model

data class UsuarioDTO(
    val id: Int?,
    val primerNombre: String,
    val segundoNombre: String,
    val primerApellido: String,
    val segundoApellido: String,
    val edad: Int,
    val tipoPersona: TipoPersona
)