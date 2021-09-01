package za.ac.nwu.translator;

import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.ExchangeMediumDto;

import java.util.List;

@Component
public interface ExchangeMediumTranslator {
    List<ExchangeMediumDto> getExchangeMediumDtos();

    ExchangeMediumDto getExchangeMediumByEmID(Integer emid);

    ExchangeMediumDto getExchangeMediumByTypeAndID(String type, Integer id);

    ExchangeMediumDto increaseExchangeMediumTotal(Integer id, Double amount);

    ExchangeMediumDto decreaseBalance(Integer id, Double amount);

    ExchangeMediumDto create(ExchangeMediumDto exchangeMedium);

    Integer checkTypeExists(Integer id, String type);
}
