package com.permission_core

import com.permission_core.controller.PermissionController

actual fun platform() = "iOS"

actual fun providePermissionController(): PermissionController {
    return IOSPermissionController()
}