package za.ac.nwu.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.ExchangeMediumDto;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.logic.flow.ViewExchangeMediumService;
import za.ac.nwu.translator.ExchangeMediumTranslator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component("viewExchangeMediumsFlow")
public class ViewExchangeMediumServiceImpl implements ViewExchangeMediumService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewExchangeMediumServiceImpl.class);
    private final ExchangeMediumTranslator exchangeMediumTranslator;

    @Autowired
    public ViewExchangeMediumServiceImpl(ExchangeMediumTranslator exchangeMediumTranslator) {
        this.exchangeMediumTranslator = exchangeMediumTranslator;
    }

    @Override
    public List<ExchangeMediumDto> getExchangeMediumByMemID(Integer id) throws SQLException {
        LOGGER.info("The input for id was {}", id);
        List<ExchangeMediumDto> exchangeMediumDtos = new ArrayList<>();
        for (Exchange_Medium exchange_medium : exchangeMediumTranslator.getExchangeMediumByMemID(id)) {
            exchangeMediumDtos.add(new ExchangeMediumDto(exchange_medium));
        }
        LOGGER.info("The return object is {} ", exchangeMediumDtos);
        return exchangeMediumDtos;
    }

    @Override
    public ExchangeMediumDto getExchangeMediumByEmID(Integer id) throws SQLException {
        LOGGER.info("The input for id is {}", id);
        Exchange_Medium exchange_medium = exchangeMediumTranslator.getExchangeMediumByEmID(id);
        ExchangeMediumDto result = null != exchange_medium ? new ExchangeMediumDto(exchange_medium) : null;
        LOGGER.info("The return object is {} ", result);
        return result;
    }

    @Override
    public ExchangeMediumDto getExchangeMediumCurrentByTypeAndID(String type, Integer id) throws SQLException {
        LOGGER.info("The input for type is {} and id is {} ", type, id);
        Exchange_Medium exchangeMedium = exchangeMediumTranslator.getExchangeMediumCurrentByTypeAndID(type, id);
        ExchangeMediumDto result = null != exchangeMedium ? new ExchangeMediumDto(exchangeMedium) : null;
        LOGGER.info("The return object is {} ", result);
        return result;
    }
}
