package com.gigrt.currencyexchanger.manager;

import com.gigrt.currencyexchanger.constants.AppConstants;
import com.gigrt.currencyexchanger.dao.CurrencyExchangeDAO;
import com.gigrt.currencyexchanger.model.ExchangeCurrency;
import com.gigrt.currencyexchanger.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class CurrencyExchangeManagerImpl implements CurrencyExchangeManager {

    private static final Logger LOG = LoggerFactory.getLogger(CurrencyExchangeManagerImpl.class);

    @Autowired
    private CurrencyExchangeDAO currencyExchangeDAO;


    @Override
    public BigDecimal convertedBaseCurrency(ExchangeCurrency currency, BigDecimal quantity) {
        LOG.info("convertedBaseCurrency");
        return quantity.divide(currency.getExchangeRate(), AppConstants.CURRENCY_SCALE, RoundingMode.CEILING);
    }

    @Override
    public BigDecimal convertedCurrency(ExchangeCurrency currency, BigDecimal quantity) {
        LOG.info("convertedCurrency");
        return quantity.multiply(currency.getExchangeRate());
    }

    @Override
    public BigDecimal calculateExchangeRate(BigDecimal originalCurrency, BigDecimal targetCurrency) {
        LOG.info("calculateExchangeRate; originalCurrency = {}, targetCurrency = {}", originalCurrency, targetCurrency);
        return targetCurrency.divide(originalCurrency, AppConstants.CURRENCY_SCALE, RoundingMode.CEILING);
    }

    @Override
    public void save(Transaction transaction) {
        LOG.info("save, {}", transaction);
        currencyExchangeDAO.save(transaction);
    }
}
