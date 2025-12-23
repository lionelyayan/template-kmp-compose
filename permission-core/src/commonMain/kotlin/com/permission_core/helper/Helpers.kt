package com.permission_core.helper

import com.permission_core.model.AppPermission

fun AppPermission.nameReadable(): String =
    when (this) {
        AppPermission.Notification -> "Notifikasi"
        AppPermission.Camera -> "Kamera"
        AppPermission.Microphone -> "Mikrofon"
        AppPermission.LocationWhenInUse,
        AppPermission.LocationAlways -> "Lokasi"
        AppPermission.MediaImages,
        AppPermission.MediaVideo,
        AppPermission.MediaAudio -> "Media"
        else -> "izin ini"
    }
