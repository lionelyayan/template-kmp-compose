package com.permission_core.ui.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.permission_core.helper.nameReadable
import com.permission_core.model.PermissionRequest

@Composable
fun PermissionDeniedDialog(
    request: PermissionRequest,
    isPermanent: Boolean,
    onRetry: () -> Unit,
    onOpenSettings: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Izin Ditolak")
        },
        text = {
            Text(
                if (isPermanent) {
                    "Izin ${request.permission.nameReadable()} telah ditolak. " +
                            "Silakan aktifkan melalui Pengaturan aplikasi."
                } else {
                    "Izin ${request.permission.nameReadable()} dibutuhkan agar fitur ini dapat berjalan dengan baik."
                }
            )
        },
        confirmButton = {
            if (isPermanent) {
                Button(onClick = onOpenSettings) {
                    Text("Buka Pengaturan")
                }
            } else {
                Button(onClick = onRetry) {
                    Text("Coba Lagi")
                }
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Tutup")
            }
        }
    )
}
