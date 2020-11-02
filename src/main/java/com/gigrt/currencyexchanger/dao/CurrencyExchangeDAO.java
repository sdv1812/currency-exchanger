package com.gigrt.currencyexchanger.dao;

import com.gigrt.currencyexchanger.model.ExchangeCurrency;
import com.gigrt.currencyexchanger.model.Transaction;

import java.util.List;

public interface CurrencyExchangeDAO {
    List<ExchangeCurrency> findAll();

    ExchangeCurrency findByName(String currency);

    void save(Transaction transaction);
}
