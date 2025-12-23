package com.sssmobile

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import com.permission_core.AndroidPermissionController
import com.permission_core.androidPermissionController
import com.sssmobile.di.moduleApp
import com.sssmobile.helpers.deeplink.DeeplinkEvent
import com.sssmobile.helpers.deeplink.DeeplinkHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        // ===============================
        // DataStore Preferences
        // ===============================
        initPreferencesDataStore(applicationContext)

        // ===============================
        // Dependency Injection (Koin)
        // ===============================
        if (GlobalContext.getOrNull() == null) {
            startKoin {
                androidContext(this@MainActivity)
                modules(moduleApp)
            }
        }

        androidPermissionController =
            AndroidPermissionController(this)

        handleIntent(intent)

        // ===============================
        // Compose Content
        // ===============================
        setContent {
            App()
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        val deeplink = intent?.getStringExtra("deeplink")
        val userData = intent?.getStringExtra("user_data")

        if (deeplink != null) {
            DeeplinkHandler.emit(
                DeeplinkEvent(
                    deeplink = deeplink,
                    userData = userData
                )
            )
        }
    }

}

//@Preview
//@Composable
//fun AppAndroidPreview() {
//    App()
//}