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

    ExchangeMediumDto decreaseExchangeMediumTotal(Integer id, Double amount);

    Integer checkTypeExists(Integer id, String type);

    ExchangeMediumDto newExchangeMedium(ExchangeMediumDto exchangeMediumDto);

    Double getExchangeMediumCurrentByTypeAndID(String type, Integer id);
}
