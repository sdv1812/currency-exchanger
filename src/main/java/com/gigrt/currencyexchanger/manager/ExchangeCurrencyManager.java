package com.gigrt.currencyexchanger.manager;

import com.gigrt.currencyexchanger.exceptions.InternalServerException;
import com.gigrt.currencyexchanger.model.Currency;

import java.math.BigDecimal;

public interface ExchangeCurrencyManager {

    BigDecimal calculateExchangeAmount(Currency sourceCurrency, Currency targetCurrency, BigDecimal quantity) throws InternalServerException;

    BigDecimal calculateExchangeRate(Currency originalCurrency, Currency targetCurrency) throws InternalServerException;

}
