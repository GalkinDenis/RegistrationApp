package com.example.app.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.app.domain.usecases.GetUserUseCase
import com.example.app.domain.usecases.SaveUserUseCase
import javax.inject.Inject

class ChangePasswordViewModel @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase,
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {



}