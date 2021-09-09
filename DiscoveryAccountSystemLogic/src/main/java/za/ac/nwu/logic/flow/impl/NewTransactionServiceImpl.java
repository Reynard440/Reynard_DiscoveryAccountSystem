package za.ac.nwu.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.ExchangeMediumDto;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.domain.persistence.Member_Transaction;
import za.ac.nwu.logic.flow.NewTransactionService;
import za.ac.nwu.logic.flow.ViewExchangeMediumService;
import za.ac.nwu.translator.MemberTransactionTranslator;
import za.ac.nwu.translator.MemberTranslator;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@Component("newMemberTransaction")
public class NewTransactionServiceImpl implements NewTransactionService {
    private final MemberTransactionTranslator memberTransactionTranslator;
    private final ViewExchangeMediumService viewExchangeMediumService;

    @Autowired
    public NewTransactionServiceImpl(MemberTransactionTranslator memberTransactionTranslator, ViewExchangeMediumService viewExchangeMediumService) {
        this.memberTransactionTranslator = memberTransactionTranslator;
        this.viewExchangeMediumService = viewExchangeMediumService;
    }

    @Override
    public MemberTransactionDto addTransactionDto(MemberTransactionDto memberTransactionDto) {
        ExchangeMediumDto exchange_medium = viewExchangeMediumService.getExchangeMediumByEmID(memberTransactionDto.getExID());

        Member_Transaction memberTransaction = memberTransactionDto.buildMemberTransaction(exchange_medium.getExchangeMedium());

        MemberTransactionDto newMemberTransaction = memberTransactionTranslator.addMemberTransaction(memberTransactionDto);

//        if (null != memberTransactionDto.getEmId()) {
//            Exchange_Medium exchangeMedium = memberTransactionDto.getMemberTransaction().getEmId().buildExchangeMedium(new);
//        }

        if(null == memberTransactionDto.getTransactionDate()){
            memberTransactionDto.setTransactionDate(LocalDate.now());
            memberTransactionDto.setDescription("Example description here.");
        }
        return newMemberTransaction;
    }
}
