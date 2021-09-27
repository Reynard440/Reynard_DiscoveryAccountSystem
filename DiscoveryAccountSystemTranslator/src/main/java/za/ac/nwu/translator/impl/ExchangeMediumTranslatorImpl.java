package za.ac.nwu.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.repo.persistence.ExchangeMediumRepository;
import za.ac.nwu.translator.ExchangeMediumTranslator;

import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Transactional
@Component
public class ExchangeMediumTranslatorImpl implements ExchangeMediumTranslator {
    private final ExchangeMediumRepository exchangeMediumRepository;
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DiscoveryDB", "root", "King6");

    @Autowired
    public ExchangeMediumTranslatorImpl(ExchangeMediumRepository exchangeMediumRepository) throws SQLException {
        this.exchangeMediumRepository = exchangeMediumRepository;
        con.setAutoCommit(false);
    }

    @Override
    public Exchange_Medium getExchangeMediumByEmID(Integer EmId) throws SQLException {
        try {
            Exchange_Medium result = exchangeMediumRepository.getByEM_ID(EmId);
            con.commit();
            return result;
        } catch (SQLException e) {
            con.rollback();
            throw new SQLException("Rollback occurred while retrieving by ID: ", e);
        }
    }

    @Override
    public void increaseExchangeMediumTotal(Integer id, double amount) throws SQLException {
        try {
            exchangeMediumRepository.increaseBalance(amount, id);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            throw new SQLException("Rollback occurred while updating the exchange medium:", e);
        }
    }

    @Override
    public void decreaseExchangeMediumTotal(Integer id, double amount) throws SQLException {
        try {
            exchangeMediumRepository.decreaseBalance(amount, id);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            throw new SQLException("Rollback occurred while updating the exchange medium:", e);
        }
    }

    @Override
    public boolean checkTypeExists(Integer id, String type) throws SQLException {
        try {
            boolean result = exchangeMediumRepository.existsByTypeAndMemID_Id(type, id);
            con.commit();
            return result;
        } catch (SQLException e) {
            con.rollback();
            throw new SQLException("Rollback occurred while checking whether an exchange medium exists:", e);
        }
    }

    @Override
    public Exchange_Medium newExchangeMedium(Exchange_Medium exchange_medium) throws SQLException {
        try {
            Exchange_Medium save = exchangeMediumRepository.save(exchange_medium);
            con.commit();
            return save;
        } catch (SQLException e) {
            con.rollback();
            throw new SQLException("Rollback occurred while creating an exchange medium:", e);
        }
    }

    @Override
    public Exchange_Medium getExchangeMediumCurrentByTypeAndID(String type, Integer id) throws SQLException {
        try {
            Exchange_Medium result = exchangeMediumRepository.getExchangeMediumCurrentByTypeAndID(type, id);
            con.commit();
            return result;
        } catch (SQLException e) {
            con.rollback();
            throw new SQLException("Rollback occurred while retrieving by ID and type: ", e);
        }
    }

    @Override
    public void configureExchangeMedium(String type, String newType, double adjust, Integer mem, Integer id) throws SQLException {
        try {
            exchangeMediumRepository.switchExchangeMedium(type, newType, adjust, mem, id);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            throw new SQLException("Rollback occurred while configuring between the exchange mediums:", e);
        }
    }
}
