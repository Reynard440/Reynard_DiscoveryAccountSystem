package za.ac.nwu.logic.flow;

import za.ac.nwu.domain.dto.ExchangeMediumDto;
import za.ac.nwu.domain.dto.MemberTransactionDto;

import java.util.List;

public interface ViewExchangeMediumService {
    List<ExchangeMediumDto> getAllExchangeMedium();
}
