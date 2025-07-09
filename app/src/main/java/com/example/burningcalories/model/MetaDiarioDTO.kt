package com.example.burningcalories.model

import java.time.LocalDate

data class MetaDiariaDTO(
    val id: Int?,
    val fecha: LocalDate,
    val metaCalorias: Int,
    val metaAguaMl: Int,
    val metaEjercicioMinutos: Int,
    val usuarioId: Int?
)