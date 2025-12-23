import Foundation
import ComposeApp

enum DeeplinkBridge {

    static func emit(
        deeplink: String,
        userData: String?
    ) {
        let event = DeeplinkEvent(
            deeplink: deeplink,
            userData: userData
        )

        DeeplinkHandler.shared.emit(event: event)
    }
}
