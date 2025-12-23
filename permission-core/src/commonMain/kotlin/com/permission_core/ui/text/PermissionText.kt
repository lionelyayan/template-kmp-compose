package com.permission_core.ui.text

import androidx.compose.runtime.Composable
import com.permission_core.model.AppPermission

@Composable
fun AppPermission.title(): String =
    when (this) {
        AppPermission.Notification -> "Izin Notifikasi"
        AppPermission.Camera -> "Izin Kamera"
        AppPermission.Microphone -> "Izin Mikrofon"

        AppPermission.MediaImages,
        AppPermission.MediaVideo,
        AppPermission.MediaAudio -> "Izin Media"

        AppPermission.LocationWhenInUse,
        AppPermission.LocationAlways,
        AppPermission.BackgroundLocation -> "Izin Lokasi"

        AppPermission.Contacts -> "Izin Kontak"

        AppPermission.CalendarRead,
        AppPermission.CalendarWrite -> "Izin Kalender"

        AppPermission.Bluetooth,
        AppPermission.BluetoothScan,
        AppPermission.BluetoothConnect -> "Izin Bluetooth"

        AppPermission.StorageRead,
        AppPermission.StorageWrite -> "Izin Penyimpanan"

        AppPermission.PhoneState,
        AppPermission.CallPhone,
        AppPermission.ReadSms,
        AppPermission.ReceiveSms,
        AppPermission.SendSms -> "Izin Telepon & SMS"

        AppPermission.ExactAlarm -> "Izin Alarm Tepat Waktu"
        AppPermission.IgnoreBatteryOptimization -> "Optimasi Baterai"

        AppPermission.AppTrackingTransparency -> "Izin Pelacakan"
        AppPermission.PhotosAddOnly -> "Izin Foto"
        AppPermission.SpeechRecognition -> "Izin Pengenalan Suara"
        AppPermission.Siri -> "Izin Siri"
        AppPermission.Biometric -> "Izin Biometrik"
    }
