package com.jithin.core.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.Properties;

/**
 * Created by Jithin on 25/10/16.
 */

public class AppConfig {
    private static AppConfig instance = null;
    private static Properties app_config = null;
    private Context context;
    private PackageInfo info = null;

    public void init(Context context) {
        this.context = context.getApplicationContext();
        AssetsPropertyReader apr = new AssetsPropertyReader(this.context);
        app_config.putAll(apr.getProperties("app_config.properties"));
        try {
            info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            set(Constants.APP_VERSION_CODE, info.versionCode + "");
            set(Constants.APP_VERSION_NAME, info.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    private AppConfig() {
        app_config = new Properties();
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public boolean isInitialized() {
        return context != null;
    }

    public int getVersionCode() {
        if (info != null)
            return info.versionCode;
        return 0;
    }

    public String getVersionName() {
        if (info != null)
            return info.versionName;
        return null;
    }

    public String getPackageName() {
        if (info != null)
            return info.packageName;
        return null;
    }


    public String getConnectionProtocol() {
        String http_protocol_string = get("http_protocol_string");
        if (http_protocol_string == null || http_protocol_string.equalsIgnoreCase("null"))
            http_protocol_string = "http";
        return http_protocol_string + "://";
    }

    public String get(String key) {
        return app_config.getProperty(key);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        String value = get(key);
        if (value != null)
            return Boolean.valueOf(value);
        else
            return defaultValue;
    }

    public String get(String key, String defaultVaue) {
        String result = app_config.getProperty(key);
        if (result == null) {
            result = defaultVaue;
        }
        return result;
    }

    public void set(String key, String value) {
        app_config.setProperty(key, value);
    }

    public void remove(String key) {
        app_config.remove(key);
    }

    public Object getObject(String key) {
        return app_config.get(key);
    }

    public String getString(String key) {
        String value = null;
        Object obj = getObject(key);
        if (obj != null) value = obj.toString();
        return value;
    }

    public void set(String key, Object value) {
        app_config.put(key, value);
    }

    public static enum APP_NAME {jithin, MRjithin, VILARA, STYLE_LOUNGE, LIBRARY}

}
