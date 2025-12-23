package com.sssmobile.views.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sssmobile.baseApp.strings.StringsProvider
import com.sssmobile.baseApp.strings.changeLanguage
import com.sssmobile.baseApp.strings.changeTheme
import com.sssmobile.baseApp.strings.login
import com.sssmobile.models.ResponseModel
import com.sssmobile.api.BaseResponse

@Composable
fun LoginScreen(
    language: String,
    onChangeLanguage: (String) -> Unit,
    isDarkMode: Boolean,
    onToggleTheme: (Boolean) -> Unit,
    onNext: () -> Unit,
    onGetUserDetailTest: () -> Unit,
    testData: BaseResponse<ResponseModel>?,
    indexTest: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            StringsProvider.getString(login, language),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(onClick = {
                onToggleTheme(!isDarkMode)
            }) {
                Text(StringsProvider.getString(changeTheme, language))
            }
            Button(onClick = {
                val newLang = if (language == "id") "en" else "id"
                onChangeLanguage(newLang)
            }) {
                Text(StringsProvider.getString(changeLanguage, language))
            }
        }
        Spacer(Modifier.height(16.dp))
        if (testData != null) {
            Text(
                "Response User:",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(8.dp))

            Text(testData.toString())
        }
        Button(onClick = { onGetUserDetailTest() }
        ) {
            Text("Request Next User Detail ke => $indexTest")
        }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = onNext
        ) {
            Text("Next Page")
        }
    }
}