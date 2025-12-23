
# Template Kotlin MultiPlatform

Template ini sengaja dibuat karena permasalahan pribadi dalam membuat aplikasi dengan Kotlin MultiPlatform yang kadang memakan waktu hanya untuk setup awal project.

Tujuannya adalah mendapatkan formula yang mudah, modular, maintenable, dan efisien dalam setup awal pembuatan aplikasi Kotlin MultiPlatform.

### Requirement
- Android Studio Otter 2 Feature Drop | 2025.2.2 (Build #AI-252.27397.103.2522.14514259, built on December 1, 2025)
- kotlin = "2.2.21"
- composeMultiplatform = "1.9.3"
- agp = "8.11.2"
- Java 17

### Platform
- Android
- iOS

### Apa saja setup dari template ini?

- UI jetpack compose
- Arsitektur MVVM (Model, View, View Model)
- Base ViewModel untuk di implement ke setiap viewmodel
- Konfigurasi Network mendukung timeout & retry
- Base Request dengan Method standar HTTP (GET, POST, PUT, PATCH, DELETE)
- Base Request opsi menyisipkan token/header
- Base Request mendukung query, raw, field
- Base Response
- Environment API untuk DEVELOPMENT, STAGGING, PRODUCTION
- Base Aplikasi untuk multi bahasa
- Base Aplikasi untuk tema gelap dan terang
- Base Aplikasi penampung string dan warna
- Disediakan folder untuk layer data (lokal dan repository)
- Setting preferences untuk menyimpan data sederhana di lokal
- Dependency Injection
- Navigation dan UI terpisah
- Lifecycle tahan ketika rotasi atau ganti tema
- Private Library permission-core tinggal pakai
- Connect firebase
- Firebase Cloud Messaging
- Set Notifikasi, termasuk deeplink

### Untuk konek/menggunakan firebase
- Buat project firebase di console.firebase.com
- Tambahkan project android dan atau ios pada firebase tersebut
- Untuk android : download google-services.json, kemudian taroh di root -> composeApp (sejajar dengan src dan build.gradle.kts)
- Untuk iOS : download GoogleService-Info.plist, kemudian taroh di root -> iosApp -> iosApp (sejajar dengan Info.plist dan iOSApp.swift)

### Note
Template ini tidak sepenuhnya baik dan benar, masih perlu  banyak pembenahan supaya bisa menjadi lebih baik lagi.
