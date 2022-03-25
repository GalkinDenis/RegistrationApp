package com.example.app.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.domain.LogInRequest
import com.example.app.domain.RegistryRequest
import com.example.app.domain.usecases.SaveUserUseCase
import com.example.app.presentation.SingleLiveEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistryViewModel @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase,
) : ViewModel() {

    private val _registryRequest = SingleLiveEvent<RegistryRequest>()
    fun registryRequest(): SingleLiveEvent<RegistryRequest> = _registryRequest

    fun registryUser(email: String, password: String) {
        viewModelScope.launch {
            _registryRequest.value = saveUserUseCase.invoke(email, password)
        }
    }
}