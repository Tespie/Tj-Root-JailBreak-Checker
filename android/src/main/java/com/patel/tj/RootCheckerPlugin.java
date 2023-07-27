package com.patel.tj;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "RootChecker")
public class RootCheckerPlugin extends Plugin {

    private RootChecker implementation = new RootChecker();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void isDeviceRooted(PluginCall call) {
        boolean isRooted = implementation.isDeviceRooted(); // Implement your rooted check logic
        JSObject ret = new JSObject();
        ret.put("isRooted", isRooted);
        call.resolve(ret);
    }
}
