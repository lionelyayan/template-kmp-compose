package com.permission_core.ui.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.permission_core.helper.nameReadable
import com.permission_core.model.PermissionDialogState
import com.permission_core.viewModel.PermissionViewModel

@Composable
fun PermissionDialogHost(
    viewModel: PermissionViewModel,
    onGranted: () -> Unit,
    onSkipped: () -> Unit
) {
    val state by viewModel.dialogState.collectAsState()

    when (state) {

        is PermissionDialogState.Requesting -> {
            val request =
                (state as PermissionDialogState.Requesting).request

            PermissionDialog(
                request = request,
                onAllow = {
                    viewModel.onAllow()
                },
                onSkip = {
                    viewModel.onSkip()
                    onSkipped()
                    viewModel.consumeResult()
                },
                onOpenSettings = {
                    viewModel.openSettings()
                }
            )
        }

        PermissionDialogState.Granted -> {
            onGranted()
            viewModel.consumeResult()
        }

        PermissionDialogState.Skipped -> {
            onSkipped()
            viewModel.consumeResult()
        }

        is PermissionDialogState.Denied -> {
            val denied = state as PermissionDialogState.Denied

            PermissionDeniedDialog(
                request = denied.request,
                isPermanent = true,
                onRetry = {},
                onOpenSettings = {
                    viewModel.openSettings()
                    viewModel.consumeResult()
                },
                onDismiss = {
                    viewModel.consumeResult()
                }
            )
        }

        is PermissionDialogState.PermanentlyDenied -> {
            val denied = state as PermissionDialogState.PermanentlyDenied

            PermissionDeniedDialog(
                request = denied.request,
                isPermanent = true,
                onRetry = {},
                onOpenSettings = {
                    viewModel.openSettings()
                    viewModel.consumeResult()
                },
                onDismiss = {
                    viewModel.consumeResult()
                }
            )
        }

        null -> Unit
    }
}
