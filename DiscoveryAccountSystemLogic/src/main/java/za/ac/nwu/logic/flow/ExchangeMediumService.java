package za.ac.nwu.logic.flow;

import za.ac.nwu.domain.dto.ExchangeMediumDto;

public interface ExchangeMediumService {
    void increaseExchangeMediumTotal(Integer id, double amount);

    void decreaseExchangeMediumTotal(Integer id, double amount);

    ExchangeMediumDto newExchangeMedium(ExchangeMediumDto exchangeMediumDto);

    Integer checkTypeExist(Integer id, String type);
}
