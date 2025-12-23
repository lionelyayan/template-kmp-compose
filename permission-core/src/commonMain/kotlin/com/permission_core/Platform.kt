package com.permission_core

import com.permission_core.controller.PermissionController

expect fun platform(): String

expect fun providePermissionController(): PermissionController