package za.ac.nwu.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import za.ac.nwu.domain.dto.ExchangeMediumDto;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.logic.flow.ViewExchangeMediumService;
import za.ac.nwu.translator.MemberTranslator;

import java.util.List;

@Transactional
@Component
public class ViewExchangeMediumServiceImpl implements ViewExchangeMediumService {

    private final MemberTranslator memberTranslator;

    @Autowired
    public ViewExchangeMediumServiceImpl(MemberTranslator memberTranslator){
        this.memberTranslator = memberTranslator;
    }

    @Override
    public List<ExchangeMediumDto> getAllExchangeMedium(){
        return memberTranslator.getExchangeMediumDtos();
    }
}
