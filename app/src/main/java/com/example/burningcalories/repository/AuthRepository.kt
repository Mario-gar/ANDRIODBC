package com.example.burningcalories.repository


import android.content.Context
import com.example.burningcalories.data.AuthApi
import com.example.burningcalories.data.RetrofitClient
import com.example.burningcalories.data.TokenManager
import com.example.burningcalories.model.AuthRequest
import com.example.burningcalories.model.RegisterRequest


class AuthRepository(private val context: Context) {
    private val api = RetrofitClient.getRetrofit(context).create(AuthApi::class.java)
    private val tokenManager = TokenManager(context)

    suspend fun login(email: String, password: String): Boolean {
        val request = AuthRequest(email, password)
        val response = api.login(request)
        tokenManager.saveToken(response.token)
        return true
    }

    suspend fun register(request: RegisterRequest): Boolean {
        val response = api.register(request)
        tokenManager.saveToken(response.token)
        return true
    }

}