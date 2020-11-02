package com.gigrt.currencyexchanger.dao;

import com.gigrt.currencyexchanger.model.ExchangeCurrency;
import com.gigrt.currencyexchanger.model.Transaction;
import com.gigrt.currencyexchanger.utility.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CurrencyExchangeDAOImpl implements CurrencyExchangeDAO {

    private static final Logger LOG = LoggerFactory.getLogger(CurrencyExchangeDAOImpl.class);

    private final EntityManager em;

    @Autowired
    public CurrencyExchangeDAOImpl(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public List<ExchangeCurrency> findAll() {
        LOG.info("findAll");
        return em.createNamedQuery("ExchangeCurrency.findAll", ExchangeCurrency.class)
                .getResultList();
    }

    @Override
    public ExchangeCurrency findByName(String currency) {
        LOG.info("findByName, currency = {}", currency);
        List<ExchangeCurrency> exchangeCurrencyList = em.createNamedQuery("ExchangeCurrency.findByName", ExchangeCurrency.class)
                .setParameter("name", currency)
                .getResultList();
        if (CollectionUtil.isNotEmpty(exchangeCurrencyList)) {
            return exchangeCurrencyList.get(0);
        }
        return null;
    }

    @Override
    public void save(Transaction transaction) {
        LOG.info("save Transaction, transaction = {}", transaction);
        Transaction savedTransaction = em.merge(transaction);
        transaction.setId(savedTransaction.getId());
    }
}
