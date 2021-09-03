package za.ac.nwu.logic.flow;

import za.ac.nwu.domain.dto.ExchangeMediumDto;

public interface ExchangeMediumService {
    ExchangeMediumDto increaseExchangeMediumTotal(Integer id, Double amount);

    ExchangeMediumDto decreaseExchangeMediumTotal(Integer id, Double amount);

    ExchangeMediumDto newExchangeMedium(ExchangeMediumDto exchangeMediumDto);

    Integer checkTypeExist(Integer id, String type);
}
