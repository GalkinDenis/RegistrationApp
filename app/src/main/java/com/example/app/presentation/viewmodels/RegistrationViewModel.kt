package com.example.app.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.domain.entities.UserRegistrationRequest
import com.example.app.domain.usecases.UserRegistrationUseCase
import com.example.app.presentation.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val userRegistrationUseCase: UserRegistrationUseCase,
) : ViewModel() {

    private val _registryRequest = SingleLiveEvent<UserRegistrationRequest>()
    fun registrationRequest(): SingleLiveEvent<UserRegistrationRequest> = _registryRequest

    fun registrationUser(email: String, password: String) {
        viewModelScope.launch {
            _registryRequest.value = userRegistrationUseCase.invoke(email, password)
        }
    }
}