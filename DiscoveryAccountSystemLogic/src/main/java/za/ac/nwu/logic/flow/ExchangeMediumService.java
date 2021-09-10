package za.ac.nwu.logic.flow;

import za.ac.nwu.domain.dto.ExchangeMediumDto;
import za.ac.nwu.domain.persistence.Exchange_Medium;

public interface ExchangeMediumService {
    void increaseExchangeMediumTotal(Integer id, double amount);

    void decreaseExchangeMediumTotal(Integer id, double amount);

    ExchangeMediumDto newExchangeMedium(ExchangeMediumDto exchangeMediumDto);

    boolean checkTypeExist(Integer id, String type);
}
