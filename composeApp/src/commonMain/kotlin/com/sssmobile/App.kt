package com.sssmobile

import androidx.compose.runtime.*
import androidx.navigation.compose.*
import com.sssmobile.baseApp.theme.ThemeApp
import com.sssmobile.data.local.preferences.SettingPreferences
import com.sssmobile.helpers.deeplink.DeeplinkHandler
import com.sssmobile.helpers.deeplink.deeplinkToRouteStack
import com.sssmobile.helpers.deeplink.navigateWithStack
import com.sssmobile.viewModels.FcmViewModel
import com.sssmobile.viewModels.PreferencesViewModel
import com.sssmobile.views.navigation.HOME_ROUTE
import com.sssmobile.views.navigation.LOGIN_ROUTE
import com.sssmobile.views.navigation.SPLASH_ROUTE
import com.sssmobile.views.navigation.homeRoute
import com.sssmobile.views.navigation.loginRoute
import com.sssmobile.views.navigation.splashRoute
import org.koin.compose.koinInject

@Composable
fun App(
) {
    val prefVM: PreferencesViewModel = koinInject()
    val isDark by prefVM.get(SettingPreferences.KEY_DARK_THEME, false).collectAsState()
    val loggedIn by prefVM.get(SettingPreferences.KEY_LOGGED_IN, false).collectAsState()
    val language by prefVM.get(SettingPreferences.KEY_LANGUAGE, "id").collectAsState()
    val token by prefVM.get(SettingPreferences.KEY_USER_TOKEN, "").collectAsState()

    val navController = rememberNavController()

    ThemeApp(
        darkTheme = isDark,
    ) {
        LaunchedEffect(Unit) {
            DeeplinkHandler.events.collect { event ->
                val stack = deeplinkToRouteStack(event.deeplink)
//                if (!loggedIn) {
//                    navController.navigate(LOGIN_ROUTE)
//                    return@collect
//                }

                navController.navigateWithStack(stack)
                event.userData?.let {
                    println("User data from notif: $it")
                    // parse JSON → ViewModel
                }
            }
        }

        NavHost(navController = navController, startDestination = SPLASH_ROUTE) {
            splashRoute(navController, prefVM)
            loginRoute(navController, prefVM)
            homeRoute(
                navController,
                prefVM
            )
        }
    }
}