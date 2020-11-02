package com.gigrt.currencyexchanger.manager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gigrt.currencyexchanger.exceptions.InternalServerException;
import com.gigrt.currencyexchanger.model.Currency;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

class ExchangeCurrencyManagerImplTest {
    private ExchangeCurrencyManager exchangeCurrencyManager;
    private Currency currency_usd;
    private Currency currency_sgd;
    private Currency currency_inr;

    @BeforeEach
    void setUp() {
        exchangeCurrencyManager = new ExchangeCurrencyManagerImpl();
        currency_usd = new Currency(1, "USD", new BigDecimal("0.742164"));
        currency_sgd = new Currency(1, "SGD", new BigDecimal("1"));
        currency_inr = new Currency(1, "INR", new BigDecimal("54.41"));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void convertedBaseCurrency() {
    }

    @Test
    void convertedCurrency() {
    }

    @Test
    void testCalculateExchangeRateSuccessful() throws InternalServerException{
        BigDecimal exchangeRate1 = exchangeCurrencyManager.calculateExchangeRate(currency_usd, currency_inr);
        BigDecimal exchangeRate2 = exchangeCurrencyManager.calculateExchangeRate(currency_inr, currency_usd);
        BigDecimal exchangeRate3 = exchangeCurrencyManager.calculateExchangeRate(currency_usd, currency_sgd);
        BigDecimal exchangeRate4 = exchangeCurrencyManager.calculateExchangeRate(currency_sgd, currency_inr);
        BigDecimal exchangeRate5 = exchangeCurrencyManager.calculateExchangeRate(currency_sgd, currency_usd);
        BigDecimal exchangeRate6 = exchangeCurrencyManager.calculateExchangeRate(currency_inr, currency_sgd);

        assertEquals(73.312638, roundValue(exchangeRate1.doubleValue()));
        assertEquals(0.013641, roundValue(exchangeRate2.doubleValue()));
        assertEquals(1.347412,roundValue(exchangeRate3.doubleValue()));
        assertEquals(54.41, roundValue(exchangeRate4.doubleValue()));
        assertEquals(0.742164, roundValue(exchangeRate5.doubleValue()));
        assertEquals(0.018379,roundValue(exchangeRate6.doubleValue()));
    }

    @Test
    void testCalculateExchangeRateToSameCurrency() throws InternalServerException{
        BigDecimal exchangeRate = exchangeCurrencyManager.calculateExchangeRate(currency_usd, currency_usd);

        assertEquals(1.0, roundValue(exchangeRate.doubleValue()));
    }

    @Test
    void testCalculateExchangeRateException(){
        currency_usd = new Currency(1, "USD", null);
        assertThrows(InternalServerException.class, () -> {
            exchangeCurrencyManager.calculateExchangeRate(currency_usd, currency_usd);
        });
    }

    @Test
    void testCalculateExchangeAmountSuccessful() throws InternalServerException{
        BigDecimal exchangeRate1 = exchangeCurrencyManager.calculateExchangeAmount(currency_usd, currency_inr,new BigDecimal("100000"));
        BigDecimal exchangeRate2 = exchangeCurrencyManager.calculateExchangeAmount(currency_inr, currency_usd ,new BigDecimal("100000"));
        BigDecimal exchangeRate3 = exchangeCurrencyManager.calculateExchangeAmount(currency_usd, currency_sgd,new BigDecimal("10002"));
        BigDecimal exchangeRate5 = exchangeCurrencyManager.calculateExchangeAmount(currency_sgd, currency_usd,new BigDecimal("0"));

        assertEquals(7331263.709948, roundValue(exchangeRate1.doubleValue()));
        assertEquals(1364.02132, roundValue(exchangeRate2.doubleValue()));
        assertEquals(13476.805666,roundValue(exchangeRate3.doubleValue()));
        assertEquals(0, roundValue(exchangeRate5.doubleValue()));
    }

    private double roundValue(double number) {
        return (double) Math.round(number* 1000000) / 1000000;
    }

}