package com.sssmobile.baseApp.strings

import com.sssmobile.baseApp.strings.language.En
import com.sssmobile.baseApp.strings.language.Id

object StringsProvider {
    fun getString(key: String, lang: String): String {
        return when (lang.lowercase()) {
            "id" -> Id.map[key] ?: ""
            "en" -> En.map[key] ?: ""
            else -> ""
        }
    }
}

const val login = "login"
const val changeTheme = "switch_theme"
const val changeLanguage = "switch_language"