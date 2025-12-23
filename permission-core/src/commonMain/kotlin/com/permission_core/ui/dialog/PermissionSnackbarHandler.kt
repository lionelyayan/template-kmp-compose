package com.permission_core.ui.dialog

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@Composable
fun PermissionSnackbarEffect(
    message: String,
    actionLabel: String?,
    onAction: () -> Unit,
    onConsumed: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(message) {
        val result = snackbarHostState.showSnackbar(
            message = message,
            actionLabel = actionLabel
        )

        if (result == SnackbarResult.ActionPerformed) {
            onAction()
        }
        onConsumed()
    }

    SnackbarHost(snackbarHostState)
}
