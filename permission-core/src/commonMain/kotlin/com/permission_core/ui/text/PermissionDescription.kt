package com.permission_core.ui.text

import androidx.compose.runtime.Composable
import com.permission_core.model.AppPermission

@Composable
fun AppPermission.description(): String =
    when (this) {

        // ===== NOTIFICATION =====
        AppPermission.Notification ->
            "Aktifkan notifikasi agar Anda tidak melewatkan informasi penting."

        // ===== CAMERA & AUDIO =====
        AppPermission.Camera ->
            "Kamera diperlukan untuk mengambil foto atau video."

        AppPermission.Microphone ->
            "Mikrofon diperlukan untuk merekam suara."

        // ===== MEDIA =====
        AppPermission.MediaImages ->
            "Akses diperlukan untuk membaca gambar dari perangkat."

        AppPermission.MediaVideo ->
            "Akses diperlukan untuk membaca video dari perangkat."

        AppPermission.MediaAudio ->
            "Akses diperlukan untuk membaca file audio dari perangkat."

        // ===== LOCATION =====
        AppPermission.LocationWhenInUse ->
            "Lokasi diperlukan saat aplikasi sedang digunakan."

        AppPermission.LocationAlways ->
            "Lokasi diperlukan meskipun aplikasi tidak sedang dibuka."

        AppPermission.BackgroundLocation ->
            "Lokasi latar belakang diperlukan untuk fitur pelacakan."

        // ===== CONTACT & CALENDAR =====
        AppPermission.Contacts ->
            "Akses kontak diperlukan untuk memilih atau menyinkronkan kontak."

        AppPermission.CalendarRead ->
            "Akses kalender diperlukan untuk melihat jadwal."

        AppPermission.CalendarWrite ->
            "Akses kalender diperlukan untuk menambahkan atau mengubah jadwal."

        // ===== BLUETOOTH =====
        AppPermission.Bluetooth ->
            "Bluetooth diperlukan untuk terhubung dengan perangkat lain."

        AppPermission.BluetoothScan ->
            "Akses diperlukan untuk memindai perangkat Bluetooth di sekitar."

        AppPermission.BluetoothConnect ->
            "Akses diperlukan untuk terhubung ke perangkat Bluetooth."

        // ===== STORAGE =====
        AppPermission.StorageRead ->
            "Akses penyimpanan diperlukan untuk membaca file."

        AppPermission.StorageWrite ->
            "Akses penyimpanan diperlukan untuk menyimpan file."

        // ===== PHONE & SMS =====
        AppPermission.PhoneState ->
            "Akses status telepon diperlukan untuk mendeteksi panggilan."

        AppPermission.CallPhone ->
            "Akses diperlukan untuk melakukan panggilan telepon."

        AppPermission.ReadSms ->
            "Akses diperlukan untuk membaca SMS."

        AppPermission.ReceiveSms ->
            "Akses diperlukan untuk menerima SMS."

        AppPermission.SendSms ->
            "Akses diperlukan untuk mengirim SMS."

        // ===== SYSTEM =====
        AppPermission.ExactAlarm ->
            "Izin ini diperlukan agar alarm dapat berjalan tepat waktu."

        AppPermission.IgnoreBatteryOptimization ->
            "Nonaktifkan optimasi baterai agar fitur berjalan stabil."

        // ===== IOS SPECIAL =====
        AppPermission.AppTrackingTransparency ->
            "Izin diperlukan untuk menampilkan iklan yang relevan."

        AppPermission.PhotosAddOnly ->
            "Akses diperlukan untuk menyimpan foto ke galeri."

        AppPermission.SpeechRecognition ->
            "Akses diperlukan untuk mengenali suara Anda."

        AppPermission.Siri ->
            "Akses diperlukan untuk integrasi dengan Siri."

        AppPermission.Biometric ->
            "Izin biometrik diperlukan untuk keamanan akun Anda."
    }
