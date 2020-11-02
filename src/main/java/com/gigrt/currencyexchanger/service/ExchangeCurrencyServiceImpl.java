package com.gigrt.currencyexchanger.service;

import com.gigrt.currencyexchanger.dao.ExchangeCurrencyDAO;
import com.gigrt.currencyexchanger.exceptions.EntityNotFoundException;
import com.gigrt.currencyexchanger.exceptions.ErrorMessages;
import com.gigrt.currencyexchanger.manager.ExchangeCurrencyManager;
import com.gigrt.currencyexchanger.model.Currency;
import com.gigrt.currencyexchanger.model.Transaction;
import com.gigrt.currencyexchanger.model.dto.ExchangeCurrencyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExchangeCurrencyServiceImpl implements ExchangeCurrencyService {

    private static final Logger LOG = LoggerFactory.getLogger(ExchangeCurrencyServiceImpl.class);

    @Autowired
    private ExchangeCurrencyDAO exchangeCurrencyDAO;

    @Autowired
    private ExchangeCurrencyManager exchangeCurrencyManager;

    @Override
    public List<Currency> findAll() {
        return exchangeCurrencyDAO.findAll();
    }

    @Override
    public ExchangeCurrencyResponse getExchangeCurrency(String fromCurrency, String toCurrency, BigDecimal quantity) throws EntityNotFoundException {
        LOG.info("getExchangeCurrency, fromCurrency = {}, toCurrency = {}, quantity = {}", fromCurrency, toCurrency, quantity);
        Currency sourceCurrency = exchangeCurrencyDAO.findByName(fromCurrency);
        if (null == sourceCurrency) {
            throw new EntityNotFoundException(String.format(ErrorMessages.CURRENCY_NOT_FOUND.getMessage(LocaleContextHolder.getLocale()), fromCurrency));
        }
        Currency targetCurrency = exchangeCurrencyDAO.findByName(toCurrency);
        if (null == targetCurrency) {
            throw new EntityNotFoundException(String.format(ErrorMessages.CURRENCY_NOT_FOUND.getMessage(LocaleContextHolder.getLocale()), toCurrency));
        }
        LOG.debug("sourceCurrency = {}", sourceCurrency);
        LOG.debug("targetCurrency = {}", targetCurrency);
        BigDecimal convertedCurrency = exchangeCurrencyManager.calculateExchangeAmount(sourceCurrency, targetCurrency, quantity);
        BigDecimal exchangeRate = exchangeCurrencyManager.calculateExchangeRate(quantity, convertedCurrency);
        return new ExchangeCurrencyResponse(fromCurrency, toCurrency, exchangeRate, quantity, convertedCurrency);
    }

    @Override
    @Transactional
    public void save(Transaction transaction) {
        LOG.info("save transaction = {}", transaction);
        exchangeCurrencyDAO.save(transaction);
    }
}
