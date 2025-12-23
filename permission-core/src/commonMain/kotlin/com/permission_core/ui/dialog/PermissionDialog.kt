package com.permission_core.ui.dialog

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.permission_core.model.PermissionRequest
import com.permission_core.model.PermissionRequirement
import com.permission_core.ui.text.description
import com.permission_core.ui.text.title

@Composable
fun PermissionDialog(
    request: PermissionRequest,
    onAllow: () -> Unit,
    onSkip: () -> Unit,
    onOpenSettings: (() -> Unit)? = null
) {
    AlertDialog(
        onDismissRequest = {
            if (request.requirement == PermissionRequirement.OPTIONAL) {
                onSkip()
            }
        },
        title = {
            Text(request.permission.title())
        },
        text = {
            Text(request.permission.description())
        },
        confirmButton = {
            Button(onClick = onAllow) {
                Text("Izinkan")
            }
        },
        dismissButton = {
            when {
                request.requirement == PermissionRequirement.OPTIONAL -> {
                    TextButton(onClick = onSkip) {
                        Text("Lewati")
                    }
                }

                onOpenSettings != null -> {
                    TextButton(onClick = onOpenSettings) {
                        Text("Buka Pengaturan")
                    }
                }
            }
        }
    )
}