package com.permission_core

import android.Manifest
import android.os.Build
import com.permission_core.model.AppPermission

object AndroidPermissionMapper {

    fun map(permission: AppPermission): Array<String> =
        when (permission) {

            // ===== NOTIFICATION =====
            AppPermission.Notification ->
                if (Build.VERSION.SDK_INT >= 33)
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS)
                else emptyArray()

            // ===== CAMERA & AUDIO =====
            AppPermission.Camera ->
                arrayOf(Manifest.permission.CAMERA)

            AppPermission.Microphone ->
                arrayOf(Manifest.permission.RECORD_AUDIO)

            // ===== MEDIA =====
            AppPermission.MediaImages ->
                if (Build.VERSION.SDK_INT >= 33)
                    arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
                else
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)

            AppPermission.MediaVideo ->
                if (Build.VERSION.SDK_INT >= 33)
                    arrayOf(Manifest.permission.READ_MEDIA_VIDEO)
                else
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)

            AppPermission.MediaAudio ->
                if (Build.VERSION.SDK_INT >= 33)
                    arrayOf(Manifest.permission.READ_MEDIA_AUDIO)
                else
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)

            // ===== LOCATION =====
            AppPermission.LocationWhenInUse ->
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )

            AppPermission.LocationAlways ->
                if (Build.VERSION.SDK_INT >= 29)
                    arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                else
                    emptyArray()

            AppPermission.BackgroundLocation ->
                if (Build.VERSION.SDK_INT >= 29)
                    arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                else
                    emptyArray()

            // ===== CONTACT & CALENDAR =====
            AppPermission.Contacts ->
                arrayOf(Manifest.permission.READ_CONTACTS)

            AppPermission.CalendarRead ->
                arrayOf(Manifest.permission.READ_CALENDAR)

            AppPermission.CalendarWrite ->
                arrayOf(Manifest.permission.WRITE_CALENDAR)

            // ===== BLUETOOTH =====
            AppPermission.Bluetooth ->
                if (Build.VERSION.SDK_INT >= 31)
                    arrayOf(Manifest.permission.BLUETOOTH_CONNECT)
                else
                    arrayOf(Manifest.permission.BLUETOOTH)

            AppPermission.BluetoothScan ->
                if (Build.VERSION.SDK_INT >= 31)
                    arrayOf(Manifest.permission.BLUETOOTH_SCAN)
                else
                    emptyArray()

            AppPermission.BluetoothConnect ->
                if (Build.VERSION.SDK_INT >= 31)
                    arrayOf(Manifest.permission.BLUETOOTH_CONNECT)
                else
                    emptyArray()

            // ===== STORAGE (LEGACY) =====
            AppPermission.StorageRead ->
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)

            AppPermission.StorageWrite ->
                if (Build.VERSION.SDK_INT < 29)
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                else
                    emptyArray()

            // ===== PHONE & SMS =====
            AppPermission.PhoneState ->
                arrayOf(Manifest.permission.READ_PHONE_STATE)

            AppPermission.CallPhone ->
                arrayOf(Manifest.permission.CALL_PHONE)

            AppPermission.ReadSms ->
                arrayOf(Manifest.permission.READ_SMS)

            AppPermission.ReceiveSms ->
                arrayOf(Manifest.permission.RECEIVE_SMS)

            AppPermission.SendSms ->
                arrayOf(Manifest.permission.SEND_SMS)

            // ===== SYSTEM =====
            AppPermission.ExactAlarm ->
                if (Build.VERSION.SDK_INT >= 31)
                    arrayOf(Manifest.permission.SCHEDULE_EXACT_ALARM)
                else
                    emptyArray()

            AppPermission.IgnoreBatteryOptimization ->
                emptyArray() // special intent, bukan runtime permission

            // ===== IOS-ONLY / LOGICAL =====
            AppPermission.AppTrackingTransparency,
            AppPermission.PhotosAddOnly,
            AppPermission.SpeechRecognition,
            AppPermission.Siri,
            AppPermission.Biometric ->
                emptyArray()
        }
}

