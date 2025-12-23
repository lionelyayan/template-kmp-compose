package com.sssmobile.viewModels

import com.sssmobile.data.repository.Test
import com.sssmobile.viewModels.baseViewModel.BaseViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: Test
) : BaseViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess = _loginSuccess.asStateFlow()

    fun login(email: String, password: String) {
        scope.launch {
            _loading.value = true
            _error.value = null

            val result = authRepository.login(email, password)
            if (result)
                _loginSuccess.value = true
            else
                _error.value = "Email atau password salah"

            _loading.value = false
        }
    }
}
