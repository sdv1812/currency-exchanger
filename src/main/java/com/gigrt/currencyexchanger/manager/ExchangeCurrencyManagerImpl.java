package com.gigrt.currencyexchanger.manager;

import com.gigrt.currencyexchanger.constants.AppConstants;
import com.gigrt.currencyexchanger.model.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class ExchangeCurrencyManagerImpl implements ExchangeCurrencyManager {

    private static final Logger LOG = LoggerFactory.getLogger(ExchangeCurrencyManagerImpl.class);


    private BigDecimal convertedBaseCurrency(Currency currency, BigDecimal quantity) {
        LOG.info("convertedBaseCurrency");
        return quantity.divide(currency.getExchangeRate(), AppConstants.CURRENCY_SCALE, RoundingMode.CEILING);
    }

    @Override
    public BigDecimal calculateExchangeAmount(Currency sourceCurrency, Currency targetCurrency, BigDecimal quantity) {
        LOG.info("convertedCurrency");
        BigDecimal convertedBaseCurrencyQuantity = convertedBaseCurrency(sourceCurrency, quantity);
        return convertedBaseCurrencyQuantity.multiply(targetCurrency.getExchangeRate());
    }

    @Override
    public BigDecimal calculateExchangeRate(BigDecimal originalCurrency, BigDecimal targetCurrency) {
        LOG.info("calculateExchangeRate; originalCurrency = {}, targetCurrency = {}", originalCurrency, targetCurrency);
        return targetCurrency.divide(originalCurrency, AppConstants.CURRENCY_SCALE, RoundingMode.CEILING);
    }
}
