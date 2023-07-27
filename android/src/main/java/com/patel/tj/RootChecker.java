package com.patel.tj;

import android.util.Log;
import android.os.Build;
import com.getcapacitor.JSObject;

public class RootChecker {

    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }

    //  public boolean isDeviceRooted() {
    //     // Implement your rooted check logic here
    //     // For example, you can check for certain system files or properties
    //     return Build.TAGS != null && Build.TAGS.contains("test-keys");
    // }

    private boolean isDeviceRooted() {
        // Check multiple possible ways to detect rooted devices

        return checkRootProperty() ||
                checkSuperUserApk() ||
                checkForTestKeys() ||
                checkForDangerousProps() ||
                checkForRootNative() ||
                checkForRootFile();
    }

    // Check if Build.TAGS contains "test-keys"
    private boolean checkForTestKeys() {
        return Build.TAGS != null && Build.TAGS.contains("test-keys");
    }

    // Check if /system/app/Superuser.apk file exists
    private boolean checkSuperUserApk() {
        String[] paths = {
                "/system/app/Superuser.apk",
                "/sbin/su",
                "/system/bin/su",
                "/system/xbin/su",
                "/data/local/xbin/su",
                "/data/local/bin/su",
                "/system/sd/xbin/su",
                "/system/bin/failsafe/su",
                "/data/local/su"
        };
        for (String path : paths) {
            if (fileExists(path)) {
                return true;
            }
        }
        return false;
    }

    // Check if dangerous properties are set
    private boolean checkForDangerousProps() {
        return checkProp("ro.debuggable") ||
                checkProp("ro.secure") ||
                checkProp("ro.adb.secure");
    }

    // Check if native code is able to access root
    private boolean checkForRootNative() {
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"/system/xbin/which", "su"});
            int exitValue = process.waitFor();
            return exitValue == 0;
        } catch (Exception e) {
            return false;
        }
    }

    // Check for common known root files
    private boolean checkForRootFile() {
        String[] paths = {
                "/system/bin",
                "/system/xbin",
                "/sbin",
                "/data/local/xbin",
                "/data/local/bin",
                "/system/sd/xbin"
        };
        for (String path : paths) {
            if (fileExists(path + "/su") || fileExists(path + "/busybox")) {
                return true;
            }
        }
        return false;
    }

    // Check if a system property is set to 1
    private boolean checkRootProperty() {
        return checkProp("ro.build.selinux") ||
                checkProp("ro.debuggable");
    }

    // Check if a system property exists and is set to 1
    private boolean checkProp(String propName) {
        try {
            Process process = Runtime.getRuntime().exec("getprop " + propName);
            process.waitFor();
            return process.exitValue() == 0;
        } catch (Exception e) {
            return false;
        }
    }

    // Check if a file exists
    private boolean fileExists(String path) {
        try {
            File file = new File(path);
            return file.exists();
        } catch (Exception e) {
            return false;
        }
    }
}
