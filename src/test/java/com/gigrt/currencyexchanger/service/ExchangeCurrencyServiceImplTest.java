package com.gigrt.currencyexchanger.service;

import com.gigrt.currencyexchanger.dao.ExchangeCurrencyDAO;
import com.gigrt.currencyexchanger.exceptions.EntityNotFoundException;
import com.gigrt.currencyexchanger.exceptions.InternalServerException;
import com.gigrt.currencyexchanger.manager.ExchangeCurrencyManager;
import com.gigrt.currencyexchanger.model.Currency;
import com.gigrt.currencyexchanger.model.Transaction;
import com.gigrt.currencyexchanger.model.dto.ExchangeCurrencyResponse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class ExchangeCurrencyServiceImplTest {

    @Autowired
    private ExchangeCurrencyService exchangeCurrencyService;

    @MockBean
    private ExchangeCurrencyDAO exchangeCurrencyDAO;

    @MockBean
    private ExchangeCurrencyManager exchangeCurrencyManager;

    private final static String USD_CURRENCY = "USD";
    private final static String SGD_CURRENCY = "SGD";
    private final static String INR_CURRENCY = "SGD";

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindAll() {
        when(exchangeCurrencyDAO.findAll()).thenReturn(Stream.of(
                new Currency(1, "SGD", new BigDecimal(1.0)),
                new Currency(2, "USD", new BigDecimal(0.742)),
                new Currency(3, "INR", new BigDecimal(54.4))
        ).collect(Collectors.toList()));
        assertEquals(3, exchangeCurrencyService.findAll().size());
    }

    @Test
    void testGetExchangeCurrencySuccessful() throws InternalServerException, EntityNotFoundException {
        BigDecimal exchangeQuantity = new BigDecimal("10002");
        Currency currency_usd = new Currency(1, USD_CURRENCY, new BigDecimal(1.0));
        Currency currency_sgd = new Currency(2, SGD_CURRENCY, new BigDecimal(0.742));
        when(exchangeCurrencyDAO.findByName(USD_CURRENCY)).thenReturn(currency_usd);
        when(exchangeCurrencyDAO.findByName(SGD_CURRENCY)).thenReturn(currency_sgd);
        when(exchangeCurrencyManager.calculateExchangeRate(currency_usd, currency_sgd)).thenReturn(new BigDecimal("1.347412"));
        when(exchangeCurrencyManager.calculateExchangeAmount(currency_usd,currency_sgd,exchangeQuantity)).thenReturn(new BigDecimal("13476.805666"));

        ExchangeCurrencyResponse expectedExchangeCurrencyResponse = new ExchangeCurrencyResponse(USD_CURRENCY, SGD_CURRENCY, new BigDecimal("1.347412") , exchangeQuantity , new BigDecimal("13476.805666"));

        ExchangeCurrencyResponse actualExchangeCurrencyResponse = exchangeCurrencyService.getExchangeCurrency(USD_CURRENCY, SGD_CURRENCY, exchangeQuantity);
        assertEquals(expectedExchangeCurrencyResponse.getExchangeRate(), expectedExchangeCurrencyResponse.getExchangeRate());
        assertEquals(expectedExchangeCurrencyResponse.getTotalCalculatedAmount(),actualExchangeCurrencyResponse.getTotalCalculatedAmount());
    }

    @Test
    void testGetExchangeCurrencyInternalServerError() throws InternalServerException, EntityNotFoundException {
        BigDecimal exchangeQuantity = new BigDecimal("10002");
        Currency currency_usd = new Currency(1, USD_CURRENCY, new BigDecimal(1.0));
        Currency currency_sgd = new Currency(2, SGD_CURRENCY, null);
        when(exchangeCurrencyDAO.findByName(USD_CURRENCY)).thenReturn(currency_usd);
        when(exchangeCurrencyDAO.findByName(SGD_CURRENCY)).thenReturn(currency_sgd);
        when(exchangeCurrencyManager.calculateExchangeRate(currency_usd, currency_sgd)).thenThrow(InternalServerException.class);

        assertThrows(InternalServerException.class, () -> {
            exchangeCurrencyService.getExchangeCurrency(USD_CURRENCY, SGD_CURRENCY, exchangeQuantity);
        });
    }

    @Test
    void testGetExchangeCurrencyEntityNotFound() throws InternalServerException, EntityNotFoundException {
        BigDecimal exchangeQuantity = new BigDecimal("10002");
        Currency currency_usd = new Currency(1, USD_CURRENCY, new BigDecimal(1.0));
        when(exchangeCurrencyDAO.findByName("Invalid")).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> {
            exchangeCurrencyService.getExchangeCurrency(USD_CURRENCY, SGD_CURRENCY, exchangeQuantity);
        });
    }

    @Test
    void testSave() {
        Transaction transaction = new Transaction(1, new Date(), new BigDecimal("1000"), "USD", new BigDecimal("1.347412000000"), "SGD", new BigDecimal("1.347412"));
        when(exchangeCurrencyDAO.save(transaction)).thenReturn(transaction);
        assertEquals(transaction, exchangeCurrencyService.save(transaction));
        verify(exchangeCurrencyDAO, times(1)).save(transaction);
    }
}