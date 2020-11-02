package com.gigrt.currencyexchanger.api;

public class ApiUrl {

    private ApiUrl() throws IllegalAccessException {
        throw new IllegalAccessException("Utility class");
    }

    public static final String API_VERSION = "v1";

    public static final String MODULE_CURRENCY_EXCHANGE = API_VERSION + "/currency-exchange";

}
