package com.permission_core.viewModel

import com.permission_core.manager.PermissionManager
import com.permission_core.model.PermissionDialogState
import com.permission_core.model.PermissionRequest
import com.permission_core.model.PermissionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PermissionViewModel(
    private val permissionManager: PermissionManager
) {

    private val _dialogState =
        MutableStateFlow<PermissionDialogState?>(null)

    val dialogState: StateFlow<PermissionDialogState?> =
        _dialogState

    fun request(request: PermissionRequest) {
        _dialogState.value =
            PermissionDialogState.Requesting(request)
    }

    fun onAllow() {
        val state =
            _dialogState.value as? PermissionDialogState.Requesting
                ?: return

        val request = state.request

        permissionManager.request(request) { result ->
            _dialogState.value = when (result) {
                PermissionState.GRANTED ->
                    PermissionDialogState.Granted

                PermissionState.DENIED ->
                    PermissionDialogState.Denied(request)

                PermissionState.PERMANENTLY_DENIED ->
                    PermissionDialogState.PermanentlyDenied(request)
            }
        }
    }

    fun onSkip() {
        _dialogState.value =
            PermissionDialogState.Skipped
    }

    fun openSettings() {
        permissionManager.openSettings()
    }

    fun consumeResult() {
        _dialogState.value = null
    }
}
