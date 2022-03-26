package com.example.app.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.domain.entities.LogInRequest
import com.example.app.domain.usecases.GetUserUseCase
import com.example.app.presentation.SingleLiveEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

class LogInViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {

    private val _loginRequest = SingleLiveEvent<LogInRequest>()
    fun loginRequest(): SingleLiveEvent<LogInRequest> = _loginRequest

    fun checkUser(emailUser: String, password: String) {
        viewModelScope.launch {
            getUserUseCase(emailUser, password).let { _loginRequest.value = it }
        }
    }
}