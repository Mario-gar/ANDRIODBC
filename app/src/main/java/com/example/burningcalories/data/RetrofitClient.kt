package com.example.burningcalories.data

import android.content.Context
import com.example.burningcalories.data.TokenManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://10.0.2.2:8181/api/"

    fun getRetrofit(context: Context): Retrofit {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val tokenManager = TokenManager(context)

        val authInterceptor = Interceptor { chain ->
            val token = tokenManager.getToken()
            val request = if (!token.isNullOrBlank()) {
                chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
            } else {
                chain.request()
            }
            chain.proceed(request)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)       // Ver solicitudes/respuestas en Logcat
            .addInterceptor(authInterceptor) // Agrega el token automáticamente
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
