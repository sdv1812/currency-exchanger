package com.gigrt.currencyexchanger.rest;

import com.gigrt.currencyexchanger.api.ApiUrl;
import com.gigrt.currencyexchanger.exceptions.EntityNotFoundException;
import com.gigrt.currencyexchanger.model.ExchangeCurrency;
import com.gigrt.currencyexchanger.model.Transaction;
import com.gigrt.currencyexchanger.model.dto.ExchangeCurrencyResponse;
import com.gigrt.currencyexchanger.model.dto.TransactionInput;
import com.gigrt.currencyexchanger.service.CurrencyExchangeService;
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
public class CurrencyExchangeRestController {
    private static final Logger LOG = LoggerFactory.getLogger(CurrencyExchangeRestController.class);

    private final CurrencyExchangeService currencyExchangeService;

    @Autowired
    public CurrencyExchangeRestController(CurrencyExchangeService currencyExchangeService) {
        this.currencyExchangeService = currencyExchangeService;
    }

    @GetMapping
    public List<ExchangeCurrency> getAllExchangeCurrencies() {
        LOG.info("getAllExchangeCurrencies");
        return currencyExchangeService.findAll();
    }

    @GetMapping("from/{from}/to/{to}/quantity/{quantity}")
    public ExchangeCurrencyResponse getConvertedAmount(
            @PathVariable(name = "from") String fromCurrency,
            @PathVariable(name = "to") String toCurrency,
            @PathVariable(name = "quantity") BigDecimal quantity
            ) throws EntityNotFoundException {
        LOG.info("getConvertedAmount, from {}, to {}, quantity {}", fromCurrency, toCurrency, quantity);
        return currencyExchangeService.getExchangeCurrency(fromCurrency, toCurrency, quantity);
    }

    @PostMapping
    public Transaction createTransaction(@Valid @RequestBody TransactionInput transactionInput) throws EntityNotFoundException {
        LOG.info("createTransaction, transaction input = {}", transactionInput);
        ExchangeCurrencyResponse exchangeCurrencyResponse = currencyExchangeService.getExchangeCurrency(
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
        currencyExchangeService.save(transaction);
        return transaction;
    }

}
