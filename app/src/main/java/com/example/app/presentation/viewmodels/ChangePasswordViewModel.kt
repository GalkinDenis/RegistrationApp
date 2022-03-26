package com.example.app.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.domain.entities.ChangePasswordRequest
import com.example.app.domain.usecases.ChangePasswordUseCase
import com.example.app.presentation.SingleLiveEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChangePasswordViewModel @Inject constructor(
    private val changePasswordUseCase: ChangePasswordUseCase,
) : ViewModel() {

    private val _changePasswordRequest = SingleLiveEvent<ChangePasswordRequest>()
    fun changePasswordRequest(): SingleLiveEvent<ChangePasswordRequest> = _changePasswordRequest

    fun changePassword(email: String, password: String, newPassword: String) {
        viewModelScope.launch {
            _changePasswordRequest.value = changePasswordUseCase.invoke(email, password, newPassword)
        }
    }
}