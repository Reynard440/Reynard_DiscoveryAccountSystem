package za.ac.nwu.translator;

import org.springframework.stereotype.Component;
import za.ac.nwu.domain.persistence.Exchange_Medium;

import java.sql.SQLException;
import java.util.List;

@Component
public interface ExchangeMediumTranslator {
    List<Exchange_Medium> getExchangeMediumByMemID(Integer id) throws SQLException;

    Exchange_Medium getExchangeMediumByEmID(Integer id) throws SQLException;

    void increaseExchangeMediumTotal(Integer id, double amount) throws SQLException;

    void decreaseExchangeMediumTotal(Integer id, double amount) throws SQLException;

    boolean checkTypeExists(Integer id, String type) throws SQLException;

    Exchange_Medium newExchangeMedium(Exchange_Medium exchange_medium) throws SQLException;

    Exchange_Medium getExchangeMediumCurrentByTypeAndID(String type, Integer id) throws SQLException;

    void configureExchangeMedium(String type, String newType, double adjust, Integer mem, Integer id, String description) throws SQLException;
}