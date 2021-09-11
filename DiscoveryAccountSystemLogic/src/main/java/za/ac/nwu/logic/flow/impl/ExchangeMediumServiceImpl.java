package za.ac.nwu.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.ExchangeMediumDto;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.logic.flow.ExchangeMediumService;
import za.ac.nwu.translator.ExchangeMediumTranslator;
import za.ac.nwu.translator.MemberTranslator;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@Component("exchangeMediumViewFlow")
public class ExchangeMediumServiceImpl implements ExchangeMediumService {
    private final ExchangeMediumTranslator exchangeMediumTranslator;
    private final MemberTranslator memberTranslator;

    @Autowired
    public ExchangeMediumServiceImpl(ExchangeMediumTranslator exchangeMediumTranslator, MemberTranslator memberTranslator){
        this.exchangeMediumTranslator = exchangeMediumTranslator;
        this.memberTranslator = memberTranslator;
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

        Member member = memberTranslator.getOneMember(exchangeMediumDto.getExchangeMediumID());

        Exchange_Medium exchangeMedium = exchangeMediumDto.buildExchangeMedium(member);

        Exchange_Medium addedExchangeMedium = exchangeMediumTranslator.newExchangeMedium(exchangeMedium);
        return new ExchangeMediumDto(addedExchangeMedium);
    }

    @Override
    public boolean checkTypeExist(Integer id, String type) {
        if (null == type) {
            type = "Miles";
        }
        return exchangeMediumTranslator.checkTypeExists(id, type);
    }
}
