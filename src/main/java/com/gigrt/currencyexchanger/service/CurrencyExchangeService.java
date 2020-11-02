package com.gigrt.currencyexchanger.service;

import com.gigrt.currencyexchanger.exceptions.EntityNotFoundException;
import com.gigrt.currencyexchanger.model.ExchangeCurrency;
import com.gigrt.currencyexchanger.model.Transaction;
import com.gigrt.currencyexchanger.model.dto.ExchangeCurrencyResponse;

import java.math.BigDecimal;
import java.util.List;

public interface CurrencyExchangeService {
    List<ExchangeCurrency> findAll();

    ExchangeCurrencyResponse getExchangeCurrency(String fromCurrency, String toCurrency, BigDecimal quantity) throws EntityNotFoundException;

    void save(Transaction transaction);
}