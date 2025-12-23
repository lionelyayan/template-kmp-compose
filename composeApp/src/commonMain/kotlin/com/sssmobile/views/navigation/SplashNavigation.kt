package com.sssmobile.views.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.sssmobile.data.local.preferences.SettingPreferences
import com.sssmobile.viewModels.PreferencesViewModel
import com.sssmobile.viewModels.SplashViewModel
import com.sssmobile.views.ui.SplashScreen
import org.koin.compose.koinInject

const val SPLASH_ROUTE = "splash"

fun NavGraphBuilder.splashRoute(
    navController: NavHostController, prefVM: PreferencesViewModel
) {

    composable(SPLASH_ROUTE) {
        val isDark by prefVM.get(SettingPreferences.KEY_DARK_THEME, false).collectAsState()
        val language by prefVM.get(SettingPreferences.KEY_LANGUAGE, "id").collectAsState()

        val splashVM: SplashViewModel = koinInject()
        val isNextActivity by splashVM.nextActivity.collectAsState()

        LaunchedEffect(isNextActivity) {
            if (isNextActivity == null) return@LaunchedEffect

            val routeTarget = if (isNextActivity == true) HOME_ROUTE else LOGIN_ROUTE

            navController.navigate(routeTarget) {
                popUpTo(SPLASH_ROUTE) { inclusive = true }
            }
        }
        SplashScreen()
    }
}