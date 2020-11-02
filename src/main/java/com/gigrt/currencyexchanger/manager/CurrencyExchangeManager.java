package com.gigrt.currencyexchanger.manager;

import com.gigrt.currencyexchanger.model.ExchangeCurrency;
import com.gigrt.currencyexchanger.model.Transaction;

import java.math.BigDecimal;

public interface CurrencyExchangeManager {
    BigDecimal convertedBaseCurrency(ExchangeCurrency currency, BigDecimal quantity);

    BigDecimal convertedCurrency(ExchangeCurrency currency, BigDecimal quantity);

    BigDecimal calculateExchangeRate(BigDecimal originalCurrency, BigDecimal targetCurrency);

    void save(Transaction transaction);
}
