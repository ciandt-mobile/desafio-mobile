package com.pereira.tiago.desafio.mobile.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

public class ConnectivityInfo {

    private static Context context;
    private static ConnectivityManager connectivityManager;

    private ConnectivityInfo(Context context) {
        // Utility class
    }

    /**
     * Initializes {@code ConnectivityInfo} with a {@code Context}. Call it in the app's
     * {@code Application} implementation. If not initialized, all methods in this class will throw
     * {@code IllegalStateException}.
     */
    public static void init(Context ctx) {
        //Validate.notNull(ctx, "context cannot be null");
        context = ctx;
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static String getMacAddress() {
        validateState();
        final WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        return wm.getConnectionInfo().getMacAddress();
    }

    public static boolean isOffline() {
        return !isConnected();
    }

    public static boolean isConnected() {
        return is3G() || isWifi();
    }

    public static boolean is3G() {
        validateState();
        final NetworkInfo mobileNetworkInfo =
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetworkInfo == null) {
            return false;
        }
        return mobileNetworkInfo.getState() == NetworkInfo.State.CONNECTED
                || mobileNetworkInfo.getState() == NetworkInfo.State.CONNECTING;
    }

    public static boolean isWifi() {
        validateState();
        final NetworkInfo wifiNetworkInfo =
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetworkInfo == null) {
            return false;
        }
        return wifiNetworkInfo.getState() == NetworkInfo.State.CONNECTED
                || wifiNetworkInfo.getState() == NetworkInfo.State.CONNECTING;
    }

    private static void validateState() {
        if (context == null) {
            throw new IllegalStateException(
                    "call #init(Context) before requesting ConnectivityInfo operations");
        }
    }
}
