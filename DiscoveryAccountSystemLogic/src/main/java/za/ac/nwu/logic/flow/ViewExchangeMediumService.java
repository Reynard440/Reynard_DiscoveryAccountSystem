package za.ac.nwu.logic.flow;

import za.ac.nwu.domain.dto.ExchangeMediumDto;

import java.sql.SQLException;
import java.util.List;

public interface ViewExchangeMediumService {
    List<ExchangeMediumDto> getExchangeMediumByEmID(Integer id) throws SQLException;

    ExchangeMediumDto getExchangeMediumCurrentByTypeAndID(String type, Integer id) throws SQLException;
}
