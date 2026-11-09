package com.streamsync.app.data.addon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.Toast;

public class AddonToggleHelper {
    private static final String PREF_NAME = "streamsync_addon_prefs";
    private static final String KEY_ENABLED_PREFIX = "addon_enabled_";

    public static boolean isAddonEnabled(Context context, String addonId) {
        if (context == null || addonId == null || addonId.isEmpty()) return true;
        try {
            SharedPreferences sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            return sp.getBoolean(KEY_ENABLED_PREFIX + addonId, true);
        } catch (Throwable t) {
            return true;
        }
    }

    public static void setAddonEnabled(Context context, String addonId, boolean enabled) {
        if (context == null || addonId == null || addonId.isEmpty()) return;
        try {
            SharedPreferences sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            sp.edit().putBoolean(KEY_ENABLED_PREFIX + addonId, enabled).apply();
        } catch (Throwable ignored) {}
    }

    public static boolean toggleAddon(Context context, String addonId) {
        boolean newState = !isAddonEnabled(context, addonId);
        setAddonEnabled(context, addonId, newState);
        return newState;
    }

    public static String getConfigUrl(String baseUrl) {
        if (baseUrl == null || baseUrl.trim().isEmpty()) return null;
        String url = baseUrl.trim();
        // Remove trailing slashes
        while (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        if (url.endsWith("/manifest.json")) {
            return url.substring(0, url.length() - "/manifest.json".length()) + "/configure";
        }
        if (url.contains("/configure")) {
            return url;
        }
        return url + "/configure";
    }

    public static void openAddonConfig(Context context, String baseUrl) {
        if (context == null) return;
        String configUrl = getConfigUrl(baseUrl);
        if (configUrl == null) {
            Toast.makeText(context, "Addon không có liên kết cấu hình", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(configUrl));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Throwable t) {
            try {
                Toast.makeText(context, "Không thể mở trang cấu hình addon", Toast.LENGTH_SHORT).show();
            } catch (Throwable ignored) {}
        }
    }
}
