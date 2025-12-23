package com.permission_core

import com.permission_core.controller.PermissionController
import com.permission_core.model.AppPermission
import com.permission_core.model.PermissionRequest
import com.permission_core.model.PermissionState
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFoundation.AVCaptureDevice
import platform.AVFoundation.AVMediaTypeAudio
import platform.AVFoundation.AVMediaTypeVideo
import platform.AVFoundation.requestAccessForMediaType
import platform.AppTrackingTransparency.ATTrackingManager
import platform.AppTrackingTransparency.ATTrackingManagerAuthorizationStatusAuthorized
import platform.Contacts.CNContactStore
import platform.Contacts.CNEntityType
import platform.EventKit.EKEntityType
import platform.EventKit.EKEventStore
import platform.Foundation.NSURL
import platform.Intents.INPreferences
import platform.Intents.INSiriAuthorizationStatusAuthorized
import platform.LocalAuthentication.LAContext
import platform.LocalAuthentication.LAPolicyDeviceOwnerAuthenticationWithBiometrics
import platform.Photos.PHAuthorizationStatusAuthorized
import platform.Photos.PHAuthorizationStatusLimited
import platform.Photos.PHPhotoLibrary
import platform.Speech.SFSpeechRecognizer
import platform.Speech.SFSpeechRecognizerAuthorizationStatus
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationOpenSettingsURLString
import platform.UIKit.UIDevice
import platform.UIKit.registerForRemoteNotifications
import platform.UserNotifications.UNAuthorizationOptionAlert
import platform.UserNotifications.UNAuthorizationOptionBadge
import platform.UserNotifications.UNAuthorizationOptionSound
import platform.UserNotifications.UNUserNotificationCenter

class IOSPermissionController : PermissionController {

    @OptIn(ExperimentalForeignApi::class)
    override fun request(
        request: PermissionRequest,
        onResult: (PermissionState) -> Unit
    ) {
        when (request.permission) {

            // ===== NOTIFICATION =====
            AppPermission.Notification -> {
                UNUserNotificationCenter.currentNotificationCenter()
                    .requestAuthorizationWithOptions(
                        UNAuthorizationOptionAlert or
                                UNAuthorizationOptionSound or
                                UNAuthorizationOptionBadge
                    ) { granted, _ ->
                        if (granted) {
                            UIApplication.sharedApplication
                                .registerForRemoteNotifications()
                            onResult(PermissionState.GRANTED)
                        } else {
                            onResult(PermissionState.DENIED)
                        }
                    }
            }

            // ===== CAMERA =====
            AppPermission.Camera -> {
                AVCaptureDevice.requestAccessForMediaType(
                    AVMediaTypeVideo
                ) { granted ->
                    onResult(
                        if (granted)
                            PermissionState.GRANTED
                        else
                            PermissionState.DENIED
                    )
                }
            }

            // ===== MICROPHONE =====
            AppPermission.Microphone -> {
                AVCaptureDevice.requestAccessForMediaType(
                    AVMediaTypeAudio
                ) { granted ->
                    onResult(
                        if (granted)
                            PermissionState.GRANTED
                        else
                            PermissionState.DENIED
                    )
                }
            }

            // ===== MEDIA / PHOTOS =====
            AppPermission.MediaImages,
            AppPermission.MediaVideo,
            AppPermission.MediaAudio,
            AppPermission.PhotosAddOnly -> {

                PHPhotoLibrary.requestAuthorization { status ->
                    onResult(
                        when (status) {
                            PHAuthorizationStatusAuthorized,
                            PHAuthorizationStatusLimited ->
                                PermissionState.GRANTED

                            else ->
                                PermissionState.DENIED
                        }
                    )
                }
            }

            // ===== LOCATION =====
            AppPermission.LocationWhenInUse,
            AppPermission.LocationAlways,
            AppPermission.BackgroundLocation -> {
                // iOS TIDAK bisa request tanpa CLLocationManager
                // Harus lewat delegate
                onResult(PermissionState.DENIED)
            }

            // ===== CONTACTS =====
            AppPermission.Contacts -> {
                CNContactStore().requestAccessForEntityType(
                    CNEntityType.CNEntityTypeContacts
                ) { granted, _ ->
                    onResult(
                        if (granted)
                            PermissionState.GRANTED
                        else
                            PermissionState.DENIED
                    )
                }
            }

            // ===== CALENDAR =====
            AppPermission.CalendarRead,
            AppPermission.CalendarWrite -> {
                EKEventStore().requestAccessToEntityType(
                    EKEntityType.EKEntityTypeEvent
                ) { granted, _ ->
                    onResult(
                        if (granted)
                            PermissionState.GRANTED
                        else
                            PermissionState.DENIED
                    )
                }
            }

            // ===== BLUETOOTH =====
            AppPermission.Bluetooth,
            AppPermission.BluetoothScan,
            AppPermission.BluetoothConnect -> {
                // Bluetooth iOS pakai CBCentralManager delegate
                onResult(PermissionState.DENIED)
            }

            // ===== SYSTEM / LOGICAL =====
            AppPermission.ExactAlarm,
            AppPermission.IgnoreBatteryOptimization -> {
                // Tidak ada konsep ini di iOS
                onResult(PermissionState.GRANTED)
            }

            // ===== TRACKING =====
            AppPermission.AppTrackingTransparency -> {
                if (isIOS14OrAbove()) {
                    ATTrackingManager
                        .requestTrackingAuthorizationWithCompletionHandler { status ->
                            onResult(
                                if (status == ATTrackingManagerAuthorizationStatusAuthorized)
                                    PermissionState.GRANTED
                                else
                                    PermissionState.DENIED
                            )
                        }
                } else {
                    onResult(PermissionState.GRANTED)
                }
            }

            // ===== SPEECH =====
            AppPermission.SpeechRecognition -> {
                SFSpeechRecognizer.requestAuthorization { status ->
                    onResult(
                        if (status == SFSpeechRecognizerAuthorizationStatus.SFSpeechRecognizerAuthorizationStatusAuthorized)
                            PermissionState.GRANTED
                        else
                            PermissionState.DENIED
                    )
                }
            }

            // ===== SIRI =====
            AppPermission.Siri -> {
                INPreferences.requestSiriAuthorization { status ->
                    onResult(
                        if (status == INSiriAuthorizationStatusAuthorized)
                            PermissionState.GRANTED
                        else
                            PermissionState.DENIED
                    )
                }
            }

            // ===== BIOMETRIC (LOGICAL) =====
            AppPermission.Biometric -> {
                val context = LAContext()
                val canEvaluate = context.canEvaluatePolicy(
                    LAPolicyDeviceOwnerAuthenticationWithBiometrics,
                    null
                )

                onResult(
                    if (canEvaluate)
                        PermissionState.GRANTED
                    else
                        PermissionState.DENIED
                )
            }

            // ===== PHONE / SMS (NOT SUPPORTED) =====
            AppPermission.PhoneState,
            AppPermission.CallPhone,
            AppPermission.ReadSms,
            AppPermission.ReceiveSms,
            AppPermission.SendSms -> {
                onResult(PermissionState.DENIED)
            }

            // ===== STORAGE (ANDROID ONLY) =====
            AppPermission.StorageRead,
            AppPermission.StorageWrite -> {
                // iOS tidak memiliki storage permission global
                // Akses file lewat UIDocumentPicker / sandbox
                onResult(PermissionState.GRANTED)
            }
        }
    }

    override fun openAppSettings() {
        UIApplication.sharedApplication.openURL(
            NSURL(string = UIApplicationOpenSettingsURLString)!!
        )
    }

    private fun isIOS14OrAbove(): Boolean {
        val version = UIDevice.currentDevice.systemVersion
        val major = version.substringBefore(".").toIntOrNull() ?: 0
        return major >= 14
    }
}

