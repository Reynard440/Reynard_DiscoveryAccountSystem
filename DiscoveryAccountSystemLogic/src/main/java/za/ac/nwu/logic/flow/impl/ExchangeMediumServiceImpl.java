package za.ac.nwu.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.ExchangeMediumDto;
import za.ac.nwu.logic.flow.ExchangeMediumService;
import za.ac.nwu.translator.ExchangeMediumTranslator;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@Component("exchangeMediumViewFlow")
public class ExchangeMediumServiceImpl implements ExchangeMediumService {
    private final ExchangeMediumTranslator exchangeMediumTranslator;

    @Autowired
    public ExchangeMediumServiceImpl(ExchangeMediumTranslator exchangeMediumTranslator){
        this.exchangeMediumTranslator = exchangeMediumTranslator;
    }

    @Override
    public void increaseExchangeMediumTotal(Integer id, double amount) {
        exchangeMediumTranslator.increaseExchangeMediumTotal(id, amount);
    }

    @Override
    public void decreaseExchangeMediumTotal(Integer id, double amount) {
        exchangeMediumTranslator.decreaseExchangeMediumTotal(id, amount);
    }

    @Override
    public ExchangeMediumDto newExchangeMedium(ExchangeMediumDto exchangeMediumDto) {
        if(null == exchangeMediumDto.getDate()){
            exchangeMediumDto.setDate(LocalDate.now());
            exchangeMediumDto.setType("MILES");
        }
        return exchangeMediumTranslator.newExchangeMedium(exchangeMediumDto);
    }

    @Override
    public Integer checkTypeExist(Integer id, String type) {
        return exchangeMediumTranslator.checkTypeExists(id, type);
    }
}
