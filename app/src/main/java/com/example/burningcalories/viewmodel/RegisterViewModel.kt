package com.example.burningcalories.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.burningcalories.data.AuthApi
import com.example.burningcalories.data.RetrofitClient
import com.example.burningcalories.model.RegisterRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class RegisterState {
    object Success : RegisterState()
    data class Error(val message: String) : RegisterState()
    object Loading : RegisterState()
    object Idle : RegisterState()
}

class RegisterViewModel(private val context: Context) : ViewModel() {

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState

    private val api = RetrofitClient.getRetrofit(context).create(AuthApi::class.java)

    fun registerUser(request: RegisterRequest) {
        _registerState.value = RegisterState.Loading

        viewModelScope.launch {
            try {
                val response = api.register(request) // <-- ahora devuelve Usuario, no AuthResponse
                // No se guarda token porque el endpoint no lo proporciona
                _registerState.value = RegisterState.Success
            } catch (e: Exception) {
                _registerState.value = RegisterState.Error("Error: ${e.localizedMessage}")
            }
        }
    }
}
