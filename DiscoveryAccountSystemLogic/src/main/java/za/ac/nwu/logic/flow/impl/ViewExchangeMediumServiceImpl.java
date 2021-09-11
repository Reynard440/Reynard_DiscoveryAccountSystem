package za.ac.nwu.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import za.ac.nwu.domain.dto.ExchangeMediumDto;
import za.ac.nwu.domain.persistence.Exchange_Medium;
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
    public ExchangeMediumDto getExchangeMediumByEmID(Integer id) {
        Exchange_Medium exchangeMedium = exchangeMediumTranslator.getExchangeMediumByEmID(id);
        return null != exchangeMedium ? new ExchangeMediumDto(exchangeMedium) : null;
    }

    @Override
    public ExchangeMediumDto getExchangeMediumCurrentByTypeAndID(String type, Integer id) {
        Exchange_Medium exchangeMedium = exchangeMediumTranslator.getExchangeMediumCurrentByTypeAndID(type, id);
        return null != exchangeMedium ? new ExchangeMediumDto(exchangeMedium) : null;
    }

    @Override
    public List<ExchangeMediumDto> getAllExchangeMedium(){
        return exchangeMediumTranslator.getExchangeMediumDtos();
    }

}
