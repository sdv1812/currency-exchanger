package com.gigrt.currencyexchanger.rest;

import com.gigrt.currencyexchanger.api.ApiUrl;
import com.gigrt.currencyexchanger.exceptions.EntityNotFoundException;
import com.gigrt.currencyexchanger.model.Currency;
import com.gigrt.currencyexchanger.model.Transaction;
import com.gigrt.currencyexchanger.model.dto.ExchangeCurrencyResponse;
import com.gigrt.currencyexchanger.model.dto.TransactionInput;
import com.gigrt.currencyexchanger.service.ExchangeCurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(ApiUrl.MODULE_CURRENCY_EXCHANGE)
public class ExchangeCurrencyRestController {
    private static final Logger LOG = LoggerFactory.getLogger(ExchangeCurrencyRestController.class);

    private final ExchangeCurrencyService exchangeCurrencyService;

    @Autowired
    public ExchangeCurrencyRestController(ExchangeCurrencyService exchangeCurrencyService) {
        this.exchangeCurrencyService = exchangeCurrencyService;
    }

    @GetMapping
    public List<Currency> getAllCurrencies() {
        LOG.info("getAllCurrencies");
        return exchangeCurrencyService.findAll();
    }

    @GetMapping("from/{from}/to/{to}/quantity/{quantity}")
    public ExchangeCurrencyResponse getConvertedAmount(
            @PathVariable(name = "from") String fromCurrency,
            @PathVariable(name = "to") String toCurrency,
            @PathVariable(name = "quantity") BigDecimal quantity
            ) throws EntityNotFoundException {
        LOG.info("getConvertedAmount, from {}, to {}, quantity {}", fromCurrency, toCurrency, quantity);
        return exchangeCurrencyService.getExchangeCurrency(fromCurrency, toCurrency, quantity);
    }

    @PostMapping
    public Transaction createTransaction(@Valid @RequestBody TransactionInput transactionInput) throws EntityNotFoundException {
        LOG.info("createTransaction, transaction input = {}", transactionInput);
        ExchangeCurrencyResponse exchangeCurrencyResponse = exchangeCurrencyService.getExchangeCurrency(
                transactionInput.getInputCurrency(),
                transactionInput.getOutputCurrency(),
                transactionInput.getInputQuantity());
        Transaction transaction = new Transaction();
        transaction.setExchangeRate(exchangeCurrencyResponse.getExchangeRate());
        transaction.setInputCurrency(exchangeCurrencyResponse.getFromCurrency());
        transaction.setInputQuantity(exchangeCurrencyResponse.getQuantity());
        transaction.setOutputCurrency(exchangeCurrencyResponse.getToCurrency());
        transaction.setOutputQuantity(exchangeCurrencyResponse.getTotalCalculatedAmount());
        transaction.setTransactionDate(new Date());
        exchangeCurrencyService.save(transaction);
        return transaction;
    }

}
