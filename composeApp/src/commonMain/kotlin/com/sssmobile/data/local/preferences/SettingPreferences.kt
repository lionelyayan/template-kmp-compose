package com.sssmobile.data.local.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingPreferences(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        const val KEY_DARK_THEME = "dark_theme"
        const val KEY_LANGUAGE = "language"
        const val KEY_LOGGED_IN = "logged_in"
        const val KEY_USER_TOKEN = "user_token"
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> dynamicKey(name: String, sample: T): Preferences.Key<T> {
        return when (sample) {
            is Boolean -> booleanPreferencesKey(name)
            is String -> stringPreferencesKey(name)
            is Int -> intPreferencesKey(name)
            is Long -> longPreferencesKey(name)
            is Float -> floatPreferencesKey(name)
            is Double -> doublePreferencesKey(name)
            else -> throw IllegalArgumentException("Unsupported type")
        } as Preferences.Key<T>
    }

    suspend fun <T> set(key: String, value: T) {
        val prefKey = dynamicKey(key, value)
        dataStore.edit { prefs ->
            prefs[prefKey] = value
        }
    }

    fun <T> get(key: String, default: T): Flow<T> {
        val prefKey = dynamicKey(key, default)
        return dataStore.data.map { prefs ->
            prefs[prefKey] ?: default
        }
    }
}
