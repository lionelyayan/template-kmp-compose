package com.permission_core.controller

import com.permission_core.model.PermissionRequest
import com.permission_core.model.PermissionState

interface PermissionController {

    fun request(
        request: PermissionRequest,
        onResult: (PermissionState) -> Unit
    )

    fun openAppSettings()
}