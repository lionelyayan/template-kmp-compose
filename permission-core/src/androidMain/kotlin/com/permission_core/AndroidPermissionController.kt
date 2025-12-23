package com.permission_core

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.permission_core.controller.PermissionController
import com.permission_core.model.PermissionRequest
import com.permission_core.model.PermissionState

class AndroidPermissionController(
    private val activity: ComponentActivity
) : PermissionController {

    private var callback: ((PermissionState) -> Unit)? = null

    private val launcher =
        activity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->

            val denied = result.filterValues { !it }.keys

            val state = when {
                denied.isEmpty() -> PermissionState.GRANTED
                denied.any {
                    !ActivityCompat.shouldShowRequestPermissionRationale(activity, it)
                } -> PermissionState.PERMANENTLY_DENIED
                else -> PermissionState.DENIED
            }

            callback?.invoke(state)
        }

    override fun request(
        request: PermissionRequest,
        onResult: (PermissionState) -> Unit
    ) {
        val permissions = AndroidPermissionMapper.map(request.permission)

        if (permissions.isEmpty()) {
            onResult(PermissionState.GRANTED)
            return
        }

        callback = onResult
        launcher.launch(permissions)
    }

    override fun openAppSettings() {
        val intent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", activity.packageName, null)
        )
        activity.startActivity(intent)
    }
}
