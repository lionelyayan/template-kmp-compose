package com.permission_core

import com.permission_core.controller.PermissionController

actual fun platform() = "Android"

lateinit var androidPermissionController: AndroidPermissionController

actual fun providePermissionController(): PermissionController {
    return androidPermissionController
}