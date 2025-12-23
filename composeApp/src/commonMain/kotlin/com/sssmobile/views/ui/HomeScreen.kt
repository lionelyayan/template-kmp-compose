package com.sssmobile.views.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.permission_core.model.AppPermission
import com.permission_core.ui.dialog.PermissionDialogHost
import com.permission_core.manager.PermissionManager
import com.permission_core.model.PermissionRequest
import com.permission_core.model.PermissionRequirement
import com.permission_core.viewModel.PermissionViewModel
import com.permission_core.providePermissionController
import com.sssmobile.baseApp.strings.StringsProvider
import com.sssmobile.baseApp.strings.changeLanguage

@Composable
fun HomeScreen(
    language: String,
    onChangeLanguage: (String) -> Unit,
    onGetFcmToken: () -> Unit
    ) {

    val permissionViewModel = remember {
        PermissionViewModel(
            PermissionManager(providePermissionController())
        )
    }

    val request = remember {
        PermissionRequest(
            permission = AppPermission.Notification,
            requirement = PermissionRequirement.OPTIONAL
        )
    }

    val request1 = remember {
        PermissionRequest(
            permission = AppPermission.Camera,
            requirement = PermissionRequirement.OPTIONAL
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            val newLang = if (language == "id") "en" else "id"
            onChangeLanguage(newLang)
        }) {
            Text(StringsProvider.getString(changeLanguage, language))
        }

        Spacer(Modifier.height(16.dp))
        Button(onClick  = onGetFcmToken) {
            Text("Get FCM Token")
        }

        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            permissionViewModel.request(request)
        }) {
            Text("Tampilkan Izin... ")
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            permissionViewModel.request(request1)
        }) {
            Text("Tampilkan Izin 1... ")
        }

        PermissionDialogHost(
            viewModel = permissionViewModel,
            onGranted = {
                println("Permission GRANTED")
            },
            onSkipped = {
                println("Permission SKIPPED")
            }
        )
    }

}