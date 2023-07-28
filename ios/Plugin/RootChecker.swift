import Foundation
import Capacitor

@objc public class RootChecker: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }

    @objc public func isDeviceJailbroken(_ call: CAPPluginCall) {
        if let cydiaURL = URL(string: "cydia://"), UIApplication.shared.canOpenURL(cydiaURL) {
            // Device is jailbroken if it can open Cydia URL
            call.resolve(["isJailbroken": true])
            return
        }

        // Check for existence of common jailbreak files
        let jailbreakFiles = [
            "/bin/bash",
            "/usr/sbin/sshd",
            "/etc/apt",
            "/usr/bin/ssh"
        ]

        for file in jailbreakFiles {
            if FileManager.default.fileExists(atPath: file) {
                call.resolve(["isJailbroken": true])
                return
            }
        }

        // Device is not jailbroken
        call.resolve(["isJailbroken": false])
    }



}
