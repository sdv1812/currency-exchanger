package com.gigrt.currencyexchanger.model.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class TransactionInput implements Serializable {
    private static final long serialVersionUID = 1L;
//    @NotNull(message = "{inputQuantity.not.empty}")
    private BigDecimal inputQuantity;
//    @NotNull(message = "{inputCurrency.not.empty}")
    private String inputCurrency;
//    @NotNull(message = "{outputCurrency.not.empty}")
    private String outputCurrency;

    public TransactionInput() {
    }

    public TransactionInput(BigDecimal inputQuantity, String inputCurrency, String outputCurrency) {
        this.inputQuantity = inputQuantity;
        this.inputCurrency = inputCurrency;
        this.outputCurrency = outputCurrency;
    }

    public BigDecimal getInputQuantity() {
        return inputQuantity;
    }

    public void setInputQuantity(BigDecimal inputQuantity) {
        this.inputQuantity = inputQuantity;
    }

    public String getInputCurrency() {
        return inputCurrency;
    }

    public void setInputCurrency(String inputCurrency) {
        this.inputCurrency = inputCurrency;
    }

    public String getOutputCurrency() {
        return outputCurrency;
    }

    public void setOutputCurrency(String outputCurrency) {
        this.outputCurrency = outputCurrency;
    }
}
