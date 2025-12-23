package com.permission_core.model

data class PermissionRequest(
    val permission: AppPermission,
    val requirement: PermissionRequirement
)
