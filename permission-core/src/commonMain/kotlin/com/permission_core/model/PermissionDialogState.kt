package com.permission_core.model

sealed class PermissionDialogState {
    data class Requesting(val request: PermissionRequest) : PermissionDialogState()
    data class Denied(val request: PermissionRequest) : PermissionDialogState()
    data class PermanentlyDenied(val request: PermissionRequest) : PermissionDialogState()
    data object Granted : PermissionDialogState()
    data object Skipped : PermissionDialogState()
}
