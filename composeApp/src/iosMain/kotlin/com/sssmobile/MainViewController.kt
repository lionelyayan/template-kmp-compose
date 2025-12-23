package com.sssmobile

import androidx.compose.ui.window.ComposeUIViewController
import com.sssmobile.di.moduleApp
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController {
    try {
        startKoin {
            modules(moduleApp)
        }
    } catch (_: Exception) {
        // berarti koin sudah berjalan → aman diabaikan
    }

    App()

}