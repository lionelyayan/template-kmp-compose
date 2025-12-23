package com.permission_core.manager

import com.permission_core.controller.PermissionController
import com.permission_core.model.PermissionRequest
import com.permission_core.model.PermissionState

class PermissionManager(
    private val controller: PermissionController
) {

    fun request(
        request: PermissionRequest,
        onResult: (PermissionState) -> Unit
    ) {
        controller.request(request) { state ->
            onResult(state)
        }
    }

    fun openSettings() {
        controller.openAppSettings()
    }
}