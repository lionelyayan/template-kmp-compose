package com.sssmobile.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.sssmobile.MainActivity
import com.sssmobile.R

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        println("ANDROID FCM TOKEN: $token")
        // kirim ke backend
    }

    override fun onMessageReceived(message: RemoteMessage) {
        println("Isi notifikasi: ${message.data}")

        val data = message.data
        val title = data["title"] ?: "Notification"
        val body = data["body"] ?: ""
        val deeplink = data["deeplink"] ?: ""
        val userData = data["user_data"] ?: ""

        showNotification(title, body, deeplink, userData)
    }

    private fun showNotification(
        title: String,
        body: String,
        deeplink: String?,
        userData: String?
    ) {
        val channelId = "default_channel"

        val manager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Default Notification",
                NotificationManager.IMPORTANCE_HIGH
            )
            manager.createNotificationChannel(channel)
        }

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra("deeplink", deeplink)
            putExtra("user_data", userData)
        }

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(body)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        manager.notify(System.currentTimeMillis().toInt(), notification)
    }
}
