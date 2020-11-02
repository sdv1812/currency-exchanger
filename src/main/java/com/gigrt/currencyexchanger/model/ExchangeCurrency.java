/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gigrt.currencyexchanger.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "exchange_currency")
@PrimaryKeyJoinColumn(name = "id")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "ExchangeCurrency.findAll", query = "SELECT e FROM ExchangeCurrency e")
        , @NamedQuery(name = "ExchangeCurrency.findById", query = "SELECT e FROM ExchangeCurrency e WHERE e.id = :id")
        , @NamedQuery(name = "ExchangeCurrency.findByName", query = "SELECT e FROM ExchangeCurrency e WHERE e.name = :name")
        , @NamedQuery(name = "ExchangeCurrency.findByExchangeRate", query = "SELECT e FROM ExchangeCurrency e WHERE e.exchangeRate = :exchangeRate")
        , @NamedQuery(name = "ExchangeCurrency.findByDate", query = "SELECT e FROM ExchangeCurrency e WHERE e.date = :date")})
public class ExchangeCurrency extends Currency implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Column(name = "id")
//    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "exchange_rate")
    private BigDecimal exchangeRate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public ExchangeCurrency() {
        super();
    }

    public ExchangeCurrency(Integer id) {
        super(id);
    }

    public ExchangeCurrency(Integer id, String name, BigDecimal exchangeRate, Date date) {
        super(id, name);
        this.exchangeRate = exchangeRate;
        this.date = date;
    }

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "ExchangeCurrency{" +
                "exchangeRate=" + exchangeRate +
                ", date=" + date +
                '}';
    }
}
