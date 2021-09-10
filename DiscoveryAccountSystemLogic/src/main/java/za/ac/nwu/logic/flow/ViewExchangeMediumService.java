package za.ac.nwu.logic.flow;

import za.ac.nwu.domain.dto.ExchangeMediumDto;

import java.util.List;

public interface ViewExchangeMediumService {
    List<ExchangeMediumDto> getAllExchangeMedium();

//    ExchangeMediumDto getExchangeMediumDtoById(Integer em_id);

    ExchangeMediumDto getExchangeMediumByEmID(Integer id);

    ExchangeMediumDto getExchangeMediumCurrentByTypeAndID(String type, Integer id);

}
