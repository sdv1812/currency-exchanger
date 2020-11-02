package com.gigrt.currencyexchanger.service;

import com.gigrt.currencyexchanger.exceptions.EntityNotFoundException;
import com.gigrt.currencyexchanger.exceptions.InternalServerException;
import com.gigrt.currencyexchanger.model.Currency;
import com.gigrt.currencyexchanger.model.Transaction;
import com.gigrt.currencyexchanger.model.dto.ExchangeCurrencyResponse;

import java.math.BigDecimal;
import java.util.List;

public interface ExchangeCurrencyService {
    List<Currency> findAll();

    ExchangeCurrencyResponse getExchangeCurrency(String fromCurrency, String toCurrency, BigDecimal quantity) throws EntityNotFoundException, InternalServerException;

    void save(Transaction transaction);
}
