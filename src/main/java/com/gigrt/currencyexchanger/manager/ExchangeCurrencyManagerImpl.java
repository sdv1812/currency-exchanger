package com.gigrt.currencyexchanger.manager;

import com.gigrt.currencyexchanger.constants.AppConstants;
import com.gigrt.currencyexchanger.exceptions.ErrorMessages;
import com.gigrt.currencyexchanger.exceptions.InternalServerException;
import com.gigrt.currencyexchanger.model.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

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
    public BigDecimal calculateExchangeRate(Currency sourceCurrency, Currency targetCurrency) throws InternalServerException {
        LOG.info("calculateExchangeRate; sourceCurrency = {}, targetCurrency = {}", sourceCurrency, targetCurrency);
        BigDecimal targetCurrencyRate = targetCurrency.getExchangeRate();
        BigDecimal sourceCurrencyRate = sourceCurrency.getExchangeRate();
        if (sourceCurrencyRate == null || targetCurrencyRate == null) {
            throw new InternalServerException(ErrorMessages.EXCHANGE_RATE_NULL.getMessage(LocaleContextHolder.getLocale()));
        }
        return targetCurrency.getExchangeRate().divide(sourceCurrency.getExchangeRate(), AppConstants.CURRENCY_SCALE, RoundingMode.CEILING);
    }
}
