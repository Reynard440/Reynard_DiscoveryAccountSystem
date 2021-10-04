package za.ac.nwu.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.repo.persistence.ExchangeMediumRepository;
import za.ac.nwu.translator.ExchangeMediumTranslator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExchangeMediumTranslatorImpl implements ExchangeMediumTranslator {
    private final ExchangeMediumRepository exchangeMediumRepository;

    @Autowired
    public ExchangeMediumTranslatorImpl(ExchangeMediumRepository exchangeMediumRepository) {
        this.exchangeMediumRepository = exchangeMediumRepository;
    }

    @Override
    public List<Exchange_Medium> getExchangeMediumByMemID(Integer id) throws SQLException {
        return new ArrayList<>(exchangeMediumRepository.getByMemID(id));
    }

    @Override
    public Exchange_Medium getExchangeMediumByEmID(Integer id) throws SQLException {
        return exchangeMediumRepository.getByEmId(id);
    }

    @Transactional(rollbackFor = { SQLException.class })
    @Override
    public void increaseExchangeMediumTotal(Integer id, double amount) throws SQLException {
        exchangeMediumRepository.increaseBalance(amount, id);
    }

    @Transactional(rollbackFor = { SQLException.class })
    @Override
    public void decreaseExchangeMediumTotal(Integer id, double amount) throws SQLException {
        exchangeMediumRepository.decreaseBalance(amount, id);
    }

    @Override
    public boolean checkTypeExists(Integer id, String type) throws RuntimeException {
        return exchangeMediumRepository.existsByTypeAndMemID_Id(type, id);
    }

    @Transactional(rollbackFor = { SQLException.class })
    @Override
    public Exchange_Medium newExchangeMedium(Exchange_Medium exchange_medium) throws SQLException {
        return exchangeMediumRepository.save(exchange_medium);
    }

    @Override
    public Exchange_Medium getExchangeMediumCurrentByTypeAndID(String type, Integer id) throws SQLException {
        return exchangeMediumRepository.getExchangeMediumCurrentByTypeAndID(type, id);
    }

    @Transactional(rollbackFor = { SQLException.class })
    @Override
    public void configureExchangeMedium(String type, String newType, double adjust, Integer mem, Integer id, String description) throws SQLException {
        exchangeMediumRepository.switchExchangeMedium(type, newType, adjust, mem, id, description);
    }
}
