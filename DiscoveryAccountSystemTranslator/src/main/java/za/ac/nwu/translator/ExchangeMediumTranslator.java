package za.ac.nwu.translator;

import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.ExchangeMediumDto;

import java.util.List;

@Component
public interface ExchangeMediumTranslator {
    List<ExchangeMediumDto> getExchangeMediumDtos();

    ExchangeMediumDto getExchangeMediumByEmID(Integer emid);

    void increaseExchangeMediumTotal(Integer id, double amount);

    void decreaseExchangeMediumTotal(Integer id, double amount);

    Integer checkTypeExists(Integer id, String type);

    ExchangeMediumDto newExchangeMedium(ExchangeMediumDto exchangeMediumDto);

    ExchangeMediumDto getExchangeMediumCurrentByTypeAndID(String type, Integer id);
}
