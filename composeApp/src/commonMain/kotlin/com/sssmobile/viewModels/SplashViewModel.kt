package com.sssmobile.viewModels

import com.sssmobile.data.repository.Test
import com.sssmobile.viewModels.baseViewModel.BaseViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SplashViewModel(
    private val authRepository: Test
) : BaseViewModel() {

    private val _nextActivity = MutableStateFlow<Boolean?>(null)
    val nextActivity = _nextActivity.asStateFlow()

    init {
        checkLogin()
    }

    private fun checkLogin() {
        scope.launch {
            _nextActivity.value = authRepository.isNextActivity()
        }
    }
}

