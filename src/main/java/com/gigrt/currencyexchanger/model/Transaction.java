/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gigrt.currencyexchanger.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kelvinzhuang
 */
@Entity
@Table(name = "transaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transaction.findAll", query = "SELECT t FROM Transaction t")
    , @NamedQuery(name = "Transaction.findById", query = "SELECT t FROM Transaction t WHERE t.id = :id")
    , @NamedQuery(name = "Transaction.findByTransactionDate", query = "SELECT t FROM Transaction t WHERE t.transactionDate = :transactionDate")
    , @NamedQuery(name = "Transaction.findByInputQuantity", query = "SELECT t FROM Transaction t WHERE t.inputQuantity = :inputQuantity")
    , @NamedQuery(name = "Transaction.findByInputCurrency", query = "SELECT t FROM Transaction t WHERE t.inputCurrency = :inputCurrency")
    , @NamedQuery(name = "Transaction.findByOutputQuantity", query = "SELECT t FROM Transaction t WHERE t.outputQuantity = :outputQuantity")
    , @NamedQuery(name = "Transaction.findByOutputCurrency", query = "SELECT t FROM Transaction t WHERE t.outputCurrency = :outputCurrency")
    , @NamedQuery(name = "Transaction.findByExchangeRate", query = "SELECT t FROM Transaction t WHERE t.exchangeRate = :exchangeRate")})
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "transaction_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "input_quantity")
    private BigDecimal inputQuantity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "input_currency")
    private String inputCurrency;
    @Basic(optional = false)
    @NotNull
    @Column(name = "output_quantity")
    private BigDecimal outputQuantity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "output_currency")
    private String outputCurrency;
    @Basic(optional = false)
    @NotNull
    @Column(name = "exchange_rate")
    private BigDecimal exchangeRate;

    public Transaction() {
    }

    public Transaction(Integer id) {
        this.id = id;
    }

    public Transaction(Integer id, Date transactionDate, BigDecimal inputQuantity, String inputCurrency, BigDecimal outputQuantity, String outputCurrency, BigDecimal exchangeRate) {
        this.id = id;
        this.transactionDate = transactionDate;
        this.inputQuantity = inputQuantity;
        this.inputCurrency = inputCurrency;
        this.outputQuantity = outputQuantity;
        this.outputCurrency = outputCurrency;
        this.exchangeRate = exchangeRate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
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

    public BigDecimal getOutputQuantity() {
        return outputQuantity;
    }

    public void setOutputQuantity(BigDecimal outputQuantity) {
        this.outputQuantity = outputQuantity;
    }

    public String getOutputCurrency() {
        return outputCurrency;
    }

    public void setOutputCurrency(String outputCurrency) {
        this.outputCurrency = outputCurrency;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gigrt.currencyexchanger.model.Transaction[ id=" + id + " ]";
    }
    
}
