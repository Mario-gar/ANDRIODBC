package com.example.burningcalories.model

import java.time.LocalDate

data class RegistroCaloriasDTO(
    val id: Int?,
    val descripcion: String,
    val calorias: Int,
    val fecha: LocalDate
)