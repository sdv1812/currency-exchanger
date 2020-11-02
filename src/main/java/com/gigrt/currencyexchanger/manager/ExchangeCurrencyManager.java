package com.gigrt.currencyexchanger.manager;

import com.gigrt.currencyexchanger.model.Currency;

import java.math.BigDecimal;

public interface ExchangeCurrencyManager {

    BigDecimal calculateExchangeAmount(Currency sourceCurrency, Currency targetCurrency, BigDecimal quantity);

    BigDecimal calculateExchangeRate(BigDecimal originalCurrency, BigDecimal targetCurrency);

}
