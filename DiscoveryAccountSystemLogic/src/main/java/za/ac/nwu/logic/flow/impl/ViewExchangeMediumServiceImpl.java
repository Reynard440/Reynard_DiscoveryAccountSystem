package za.ac.nwu.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import za.ac.nwu.domain.dto.ExchangeMediumDto;
import za.ac.nwu.logic.flow.ViewExchangeMediumService;
import za.ac.nwu.translator.ExchangeMediumTranslator;

import java.util.List;

@Transactional
@Component("ViewExchangeMediums")
public class ViewExchangeMediumServiceImpl implements ViewExchangeMediumService {

    private final ExchangeMediumTranslator exchangeMediumTranslator;

    @Autowired
    public ViewExchangeMediumServiceImpl(ExchangeMediumTranslator exchangeMediumTranslator) {
        this.exchangeMediumTranslator = exchangeMediumTranslator;
    }

    @Override
    public List<ExchangeMediumDto> getAllExchangeMedium(){
        return exchangeMediumTranslator.getExchangeMediumDtos();
    }

    @Override
    public ExchangeMediumDto getExchangeMediumDtoById(Integer emid) {
        return exchangeMediumTranslator.getExchangeMediumByEmID(emid);
    }
}
