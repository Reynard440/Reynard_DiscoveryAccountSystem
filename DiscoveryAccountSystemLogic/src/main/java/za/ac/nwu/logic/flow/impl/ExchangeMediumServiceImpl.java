package za.ac.nwu.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.ExchangeMediumDto;
import za.ac.nwu.logic.flow.ExchangeMediumService;
import za.ac.nwu.translator.ExchangeMediumTranslator;

import javax.transaction.Transactional;

@Transactional
@Component("exchangeMediumViewFlow")
public class ExchangeMediumServiceImpl implements ExchangeMediumService {

    private final ExchangeMediumTranslator exchangeMediumTranslator;

    @Autowired
    public ExchangeMediumServiceImpl(ExchangeMediumTranslator exchangeMediumTranslator){
        this.exchangeMediumTranslator = exchangeMediumTranslator;
    }

    @Override
    public ExchangeMediumDto increaseExchangeMediumTotal(Integer id, Double amount) {
        return exchangeMediumTranslator.increaseExchangeMediumTotal(id, amount);
    }

    @Override
    public ExchangeMediumDto decreaseExchangeMediumTotal(Integer id, Double amount) {
        return exchangeMediumTranslator.decreaseExchangeMediumTotal(id, amount);
    }

    @Override
    public ExchangeMediumDto newExchangeMedium(ExchangeMediumDto exchangeMediumDto) {
        return exchangeMediumTranslator.newExchangeMedium(exchangeMediumDto);
    }

    @Override
    public Integer checkTypeExist(Integer id, String type) {
        return exchangeMediumTranslator.checkTypeExists(id, type);
    }
}
