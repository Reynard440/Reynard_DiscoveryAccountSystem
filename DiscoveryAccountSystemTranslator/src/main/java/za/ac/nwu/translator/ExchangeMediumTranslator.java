package za.ac.nwu.translator;

import org.springframework.stereotype.Component;
import za.ac.nwu.domain.persistence.Exchange_Medium;

import java.sql.SQLException;

@Component
public interface ExchangeMediumTranslator {
    Exchange_Medium getExchangeMediumByEmID(Integer emid) throws SQLException;

    void increaseExchangeMediumTotal(Integer id, double amount) throws SQLException;

    void decreaseExchangeMediumTotal(Integer id, double amount) throws SQLException;

    boolean checkTypeExists(Integer id, String type) throws SQLException;

    Exchange_Medium newExchangeMedium(Exchange_Medium exchange_medium) throws SQLException;

    Exchange_Medium getExchangeMediumCurrentByTypeAndID(String type, Integer id) throws SQLException;

    void configureExchangeMedium(String type, String newType, double adjust, Integer mem, Integer id) throws SQLException;
}