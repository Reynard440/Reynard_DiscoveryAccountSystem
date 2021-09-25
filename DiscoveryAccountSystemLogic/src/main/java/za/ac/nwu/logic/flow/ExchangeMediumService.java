package za.ac.nwu.logic.flow;

import za.ac.nwu.domain.dto.ExchangeMediumDto;

import java.sql.SQLException;

public interface ExchangeMediumService {
    void increaseExchangeMediumTotal(Integer id, double amount) throws SQLException;

    void decreaseExchangeMediumTotal(Integer id, double amount) throws SQLException;

    ExchangeMediumDto newExchangeMedium(ExchangeMediumDto exchangeMediumDto) throws SQLException;

    boolean checkTypeExist(Integer id, String type);

    void configureExchangeMedium(String type, String newType, double adjust, Integer mem, Integer id) throws SQLException;
}
