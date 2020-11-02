package com.gigrt.currencyexchanger.dao;

import com.gigrt.currencyexchanger.model.Currency;
import com.gigrt.currencyexchanger.model.Transaction;
import com.gigrt.currencyexchanger.utility.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ExchangeCurrencyDAOImpl implements ExchangeCurrencyDAO {

    private static final Logger LOG = LoggerFactory.getLogger(ExchangeCurrencyDAOImpl.class);

    private final EntityManager em;

    @Autowired
    public ExchangeCurrencyDAOImpl(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public List<Currency> findAll() {
        LOG.info("findAll");
        return em.createNamedQuery("Currency.findAll", Currency.class)
                .getResultList();
    }

    @Override
    public Currency findByName(String currency) {
        LOG.info("findByName, currency = {}", currency);
        List<Currency> exchangeCurrencyList = em.createNamedQuery("Currency.findByName", Currency.class)
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
