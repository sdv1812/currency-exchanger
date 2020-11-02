package com.gigrt.currencyexchanger.exceptions;


import com.gigrt.currencyexchanger.utility.ResourceBundleUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public enum ErrorMessages {
    NOT_READABLE("exception.not.readable"),
    INVALID_FIELDS("exception.constraint.violation"),
    CURRENCY_NOT_FOUND("exception.currency.not.found");

    private static final Logger logger = LoggerFactory.getLogger(ErrorMessages.class);

    private final String key;

    ErrorMessages(String key) {
        this.key = key;
    }

    public String getMessage(Locale locale) {
        return ResourceBundleUtil.getValueFromBundle("messages", this.key, locale);
    }
}

