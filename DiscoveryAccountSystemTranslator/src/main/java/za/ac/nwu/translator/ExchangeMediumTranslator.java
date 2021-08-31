package za.ac.nwu.translator;

import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.ExchangeMediumDto;

import java.util.List;

@Component
public interface ExchangeMediumTranslator {
    List<ExchangeMediumDto> getExchangeMediumDtos();
}
