package com.gigrt.currencyexchanger.utility;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleUtil {
    private ResourceBundleUtil() {
        throw new UnsupportedOperationException("This is a utility class!");
    }

    public static String getValueFromBundle(String bundleName, String key, Locale locale) {
        ResourceBundle labels = ResourceBundle.getBundle(bundleName, locale);
        return labels.getString(key);
    }

    public static String getValueFromBundle(String bundleName, String key) {
        ResourceBundle labels = ResourceBundle.getBundle(bundleName);
        return labels.getString(key);
    }
}
