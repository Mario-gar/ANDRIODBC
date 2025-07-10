package com.example.burningcalories.data

import com.example.burningcalories.model.AuthRequest
import com.example.burningcalories.model.AuthResponse
import com.example.burningcalories.model.RegisterRequest
import com.example.burningcalories.model.UsuarioDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body request: AuthRequest): AuthResponse

    @POST("usuario/save")
    suspend fun register(@Body request: RegisterRequest): UsuarioDTO
}