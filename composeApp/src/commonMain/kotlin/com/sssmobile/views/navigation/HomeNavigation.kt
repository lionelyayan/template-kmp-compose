package com.sssmobile.views.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.permission_core.controller.PermissionController
import com.permission_core.manager.PermissionManager
import com.sssmobile.data.local.preferences.SettingPreferences
import com.sssmobile.viewModels.FcmViewModel
import com.sssmobile.viewModels.PreferencesViewModel
import com.sssmobile.views.ui.HomeScreen
import org.koin.compose.koinInject

const val HOME_ROUTE = "home"

fun NavGraphBuilder.homeRoute(
    navController: NavHostController,
    prefVM: PreferencesViewModel
) {
    composable(HOME_ROUTE) {
        val fcmVM: FcmViewModel = koinInject()

        val isDark by prefVM.get(SettingPreferences.KEY_DARK_THEME, false).collectAsState()
        val language by prefVM.get(SettingPreferences.KEY_LANGUAGE, "id").collectAsState()

        HomeScreen(
            language = language,
            onChangeLanguage = { newLang ->
                prefVM.set(SettingPreferences.KEY_LANGUAGE, newLang)
            },
            onGetFcmToken = {
                fcmVM.fetchFcmToken()
            }
        )
    }
}