package com.example.app.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.domain.LogInRequest
import com.example.app.domain.usecases.GetUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LogInViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {

    private val _loginRequest: MutableStateFlow<LogInRequest> = MutableStateFlow(LogInRequest.Pending)
    fun loginRequest(): StateFlow<LogInRequest> = _loginRequest

    fun checkUser(emailUser: String, password: String) {
        viewModelScope.launch {
            _loginRequest.value = getUserUseCase(emailUser, password)
        }
    }

}