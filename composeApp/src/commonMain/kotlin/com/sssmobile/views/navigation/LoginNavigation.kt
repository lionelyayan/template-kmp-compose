package com.sssmobile.views.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.sssmobile.data.local.preferences.SettingPreferences
import com.sssmobile.viewModels.PreferencesViewModel
import com.sssmobile.viewModels.baseViewModel.TestViewModel
import com.sssmobile.views.ui.LoginScreen
import org.koin.compose.koinInject

const val LOGIN_ROUTE = "login"

fun NavGraphBuilder.loginRoute(
    navController: NavHostController,
    prefVM: PreferencesViewModel,
) {
    composable(LOGIN_ROUTE) {
        val isDark by prefVM.get(SettingPreferences.KEY_DARK_THEME, false).collectAsState()
        val language by prefVM.get(SettingPreferences.KEY_LANGUAGE, "id").collectAsState()

        val testVM: TestViewModel = koinInject()
        val testData by testVM.test.collectAsState()
        val indexTest by testVM.indexTest.collectAsState()

        LoginScreen(
            language = language,
            onChangeLanguage = { newLang ->
                prefVM.set(SettingPreferences.KEY_LANGUAGE, newLang)
            },
            isDarkMode = isDark,
            onToggleTheme = { newThemeMode ->
                prefVM.set(SettingPreferences.KEY_DARK_THEME, newThemeMode)
            },
            onNext = { navController.navigate(HOME_ROUTE) },
            onGetUserDetailTest = {
                testVM.nextUserDetailTest()
            },
            testData = testData,
            indexTest = indexTest
        )
    }
}