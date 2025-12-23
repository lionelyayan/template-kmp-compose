package com.sssmobile.viewModels

import com.sssmobile.data.local.preferences.SettingPreferences
import com.sssmobile.viewModels.baseViewModel.BaseViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PreferencesViewModel (
    private val prefs: SettingPreferences
) : BaseViewModel() {
    private val cache = mutableMapOf<String, StateFlow<Any?>>()

    @Suppress("UNCHECKED_CAST")
    fun <T> get(key: String, default: T): StateFlow<T> {
        // Jika sudah di-cache, kembalikan
        cache[key]?.let {
            return it as StateFlow<T>
        }

        // Flow dari datastore
        val flow = prefs.get(key, default)

        // Konversi ke StateFlow
        val stateFlow = flow.stateIn(
            scope,
            SharingStarted.Eagerly,
            default
        )

        // Simpan ke cache supaya tidak membuat ulang
        cache[key] = stateFlow as StateFlow<Any?>

        return stateFlow
    }

    fun <T> set(key: String, value: T) {
        scope.launch {
            prefs.set(key, value)
        }
    }
}