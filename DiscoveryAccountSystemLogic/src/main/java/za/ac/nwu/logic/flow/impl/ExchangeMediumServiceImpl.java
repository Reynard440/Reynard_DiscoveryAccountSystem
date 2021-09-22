package za.ac.nwu.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.ExchangeMediumDto;
import za.ac.nwu.domain.dto.MemberDto;
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
    public void increaseExchangeMediumTotal(Integer id, double amount)  {
        exchangeMediumTranslator.increaseExchangeMediumTotal(id, amount);
    }

    @Override
    public void decreaseExchangeMediumTotal(Integer id, double amount) {
        exchangeMediumTranslator.decreaseExchangeMediumTotal(id, amount);
    }

    @Override
    public ExchangeMediumDto newExchangeMedium(ExchangeMediumDto exchangeMediumDto) {
        if(null == exchangeMediumDto.getExchangeMediumID()){
            exchangeMediumDto.setExchangeMediumID(1);
            exchangeMediumDto.setDate(LocalDate.now());
            exchangeMediumDto.setType("Miles");
            exchangeMediumDto.setBalance(40);
            exchangeMediumDto.setDescription("This is a new Discovery currency type that keeps track of all your MILES");
            exchangeMediumDto.setMemID(new MemberDto(1));
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

    @Override
    public void configureExchangeMedium(String type, String newType, double adjust, Integer mem, Integer id) {
        try {
            //Exchange_Medium exchange_medium = exchangeMediumTranslator.getExchangeMediumByEmID(id);
            exchangeMediumTranslator.configureExchangeMedium(type, newType, adjust, mem, id);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while configuring to new exchange medium", e);
        }
    }
}
