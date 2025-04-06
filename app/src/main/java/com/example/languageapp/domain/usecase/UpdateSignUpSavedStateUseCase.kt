package com.example.languageapp.domain.usecase

import com.example.languageapp.data.models.SavedSignUpState
import com.example.languageapp.domain.AuthRepository
import com.example.languageapp.presentation.screens.choosePassword.ChoosePasswordAction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateSignUpSavedStateUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun invoke(
        firstName: String? = null,
        secondName: String? = null,
        email: String? = null,
        password: String? = null,
        confirmPassword: String? = null
    ) {
        withContext(Dispatchers.IO) {
            authRepository.updateSavedSignUpState(
                firstName,
                secondName,
                email,
                password,
                confirmPassword
            )
        }
    }
}