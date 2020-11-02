package com.gigrt.currencyexchanger.service;

import com.gigrt.currencyexchanger.dao.CurrencyExchangeDAO;
import com.gigrt.currencyexchanger.exceptions.EntityNotFoundException;
import com.gigrt.currencyexchanger.exceptions.ErrorMessages;
import com.gigrt.currencyexchanger.manager.CurrencyExchangeManager;
import com.gigrt.currencyexchanger.model.ExchangeCurrency;
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
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService{

    private static final Logger LOG = LoggerFactory.getLogger(CurrencyExchangeServiceImpl.class);

    @Autowired
    private CurrencyExchangeDAO currencyExchangeDAO;

    @Autowired
    private CurrencyExchangeManager currencyExchangeManager;

    @Override
    public List<ExchangeCurrency> findAll() {
        return currencyExchangeDAO.findAll();
    }

    @Override
    public ExchangeCurrencyResponse getExchangeCurrency(String fromCurrency, String toCurrency, BigDecimal quantity) throws EntityNotFoundException {
        LOG.info("getExchangeCurrency, fromCurrency = {}, toCurrency = {}, quantity = {}", fromCurrency, toCurrency, quantity);
        ExchangeCurrency sourceCurrency = currencyExchangeDAO.findByName(fromCurrency);
        if (null == sourceCurrency) {
            throw new EntityNotFoundException(String.format(ErrorMessages.CURRENCY_NOT_FOUND.getMessage(LocaleContextHolder.getLocale()), fromCurrency));
        }
        ExchangeCurrency targetCurrency = currencyExchangeDAO.findByName(toCurrency);
        if (null == targetCurrency) {
            throw new EntityNotFoundException(String.format(ErrorMessages.CURRENCY_NOT_FOUND.getMessage(LocaleContextHolder.getLocale()), toCurrency));
        }
        LOG.debug("sourceCurrency = {}", sourceCurrency);
        LOG.debug("targetCurrency = {}", targetCurrency);
        BigDecimal convertedBaseCurrency = currencyExchangeManager.convertedBaseCurrency(sourceCurrency, quantity);
        BigDecimal convertedCurrency = currencyExchangeManager.convertedCurrency(targetCurrency, convertedBaseCurrency);
        BigDecimal exchangeRate = currencyExchangeManager.calculateExchangeRate(quantity, convertedCurrency);
        return new ExchangeCurrencyResponse(fromCurrency, toCurrency, exchangeRate, quantity, convertedCurrency);
    }

    @Override
    @Transactional
    public void save(Transaction transaction) {
        LOG.info("save transaction = {}", transaction);
        currencyExchangeManager.save(transaction);
    }


}
