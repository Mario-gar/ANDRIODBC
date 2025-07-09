package com.example.burningcalories.model

import java.time.LocalDate

data class RegistroAguaDTO(
    val id: Int?,
    val fecha: LocalDate,
    val cantidadMililitros: Int,
    val usuarioId: Int?
)