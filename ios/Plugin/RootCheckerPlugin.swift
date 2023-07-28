import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(RootCheckerPlugin)
public class RootCheckerPlugin: CAPPlugin {
    private let implementation = RootChecker()

    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": implementation.echo(value)
        ])
    }

    @objc func isDeviceRooted(_ call: CAPPluginCall) {
        // Your existing Android rooted check implementation
        // ...

        // iOS jailbreak check
        let jailbreakChecker = TJJailbreakChecker()
        jailbreakChecker.isDeviceJailbroken(call)
    }


}
