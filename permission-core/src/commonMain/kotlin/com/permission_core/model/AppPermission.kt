package com.permission_core.model

sealed class AppPermission {

    // ===== NOTIFICATION =====
    data object Notification : AppPermission()

    // ===== CAMERA & AUDIO =====
    data object Camera : AppPermission()
    data object Microphone : AppPermission()

    // ===== MEDIA =====
    data object MediaImages : AppPermission()
    data object MediaVideo : AppPermission()
    data object MediaAudio : AppPermission()

    // ===== LOCATION =====
    data object LocationWhenInUse : AppPermission()
    data object LocationAlways : AppPermission()
    data object BackgroundLocation : AppPermission()

    // ===== CONTACT & CALENDAR =====
    data object Contacts : AppPermission()
    data object CalendarRead : AppPermission()
    data object CalendarWrite : AppPermission()

    // ===== BLUETOOTH =====
    data object Bluetooth : AppPermission()
    data object BluetoothScan : AppPermission()
    data object BluetoothConnect : AppPermission()

    // ===== STORAGE (ANDROID LEGACY) =====
    data object StorageRead : AppPermission()
    data object StorageWrite : AppPermission()

    // ===== PHONE / SMS =====
    data object PhoneState : AppPermission()
    data object CallPhone : AppPermission()
    data object ReadSms : AppPermission()
    data object ReceiveSms : AppPermission()
    data object SendSms : AppPermission()

    // ===== SYSTEM =====
    data object ExactAlarm : AppPermission()
    data object IgnoreBatteryOptimization : AppPermission()

    // ===== IOS SPECIAL =====
    data object AppTrackingTransparency : AppPermission()
    data object PhotosAddOnly : AppPermission()
    data object SpeechRecognition : AppPermission()
    data object Siri : AppPermission()
    data object Biometric : AppPermission()
}
