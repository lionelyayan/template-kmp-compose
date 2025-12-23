package com.sssmobile.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.sssmobile.createPreferencesDataStore
import com.sssmobile.data.local.preferences.SettingPreferences
import com.sssmobile.data.repository.Test
import com.sssmobile.fcm.PushTokenProvider
import com.sssmobile.fcm.PushTokenRepository
import com.sssmobile.fcm.providePushTokenProvider
import com.sssmobile.viewModels.FcmViewModel
import com.sssmobile.viewModels.LoginViewModel
import com.sssmobile.viewModels.PreferencesViewModel
import com.sssmobile.viewModels.SplashViewModel
import com.sssmobile.viewModels.baseViewModel.TestViewModel
import org.koin.dsl.module

val moduleApp = module {

    // DataStore Preferences
    single<DataStore<Preferences>> { createPreferencesDataStore() }
    single { SettingPreferences( dataStore = get() ) }
    single<PushTokenProvider> { providePushTokenProvider() }

    // Repository
    single { Test() }
    single { PushTokenRepository(get()) }

    // ViewModels
    single { TestViewModel(get()) }
    single { PreferencesViewModel(get()) }
    single { FcmViewModel(get()) }
    single { SplashViewModel(get()) }
    single { LoginViewModel(get()) }
}