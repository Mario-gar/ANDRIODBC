package com.example.burningcalories.model

import java.time.LocalDate

data class EjercicioRealizadoDTO(
    val id: Int?,
    val tipo: String,
    val duracionMinutos: Int,
    val caloriasQuemadas: Int,
    val fecha: LocalDate,
    val usuarioId: Int?
)