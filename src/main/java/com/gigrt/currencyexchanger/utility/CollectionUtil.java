package com.gigrt.currencyexchanger.utility;

import java.util.Collection;

public class CollectionUtil {

    private CollectionUtil() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}