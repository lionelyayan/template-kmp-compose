import UIKit
import FirebaseCore
import FirebaseMessaging
import UserNotifications

class AppDelegate: NSObject,
                   UIApplicationDelegate,
                   UNUserNotificationCenterDelegate,
                   MessagingDelegate {

    // ===============================
    // App Launch
    // ===============================
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {

        FirebaseApp.configure()

        UNUserNotificationCenter.current().delegate = self
        Messaging.messaging().delegate = self

        UNUserNotificationCenter.current().requestAuthorization(
            options: [.alert, .sound, .badge]
        ) { granted, _ in
            if granted {
                DispatchQueue.main.async {
                    application.registerForRemoteNotifications()
                }
            }
        }

        return true
    }

    // ===============================
    // Foreground notification
    // ===============================
    func userNotificationCenter(
        _ center: UNUserNotificationCenter,
        willPresent notification: UNNotification,
        withCompletionHandler completionHandler:
            @escaping (UNNotificationPresentationOptions) -> Void
    ) {
        handleDeeplink(notification.request.content.userInfo)
        completionHandler([.banner, .sound])
    }

    // ===============================
    // Tap / Background / Killed
    // ===============================
    func userNotificationCenter(
        _ center: UNUserNotificationCenter,
        didReceive response: UNNotificationResponse,
        withCompletionHandler completionHandler: @escaping () -> Void
    ) {
        handleDeeplink(response.notification.request.content.userInfo)
        completionHandler()
    }

    // ===============================
    // Deeplink handler
    // ===============================
    private func handleDeeplink(_ userInfo: [AnyHashable: Any]) {
        guard let deeplink = userInfo["deeplink"] as? String else { return }
        let userData = userInfo["user_data"] as? String

        print("iOS Deeplink:", deeplink)
        print("iOS UserData:", userData ?? "-")

        DeeplinkBridge.emit(
            deeplink: deeplink,
            userData: userData
        )
    }
}
