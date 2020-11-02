package com.gigrt.currencyexchanger.dao;

import com.gigrt.currencyexchanger.model.Currency;
import com.gigrt.currencyexchanger.model.Transaction;

import java.util.List;

public interface ExchangeCurrencyDAO {
    List<Currency> findAll();

    Currency findByName(String currency);

    void save(Transaction transaction);
}
