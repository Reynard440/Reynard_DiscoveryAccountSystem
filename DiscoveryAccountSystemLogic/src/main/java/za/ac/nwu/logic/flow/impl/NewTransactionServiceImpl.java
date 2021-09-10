package za.ac.nwu.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.ExchangeMediumDto;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.domain.persistence.Member_Transaction;
import za.ac.nwu.logic.flow.NewTransactionService;
import za.ac.nwu.logic.flow.ViewExchangeMediumService;
import za.ac.nwu.translator.ExchangeMediumTranslator;
import za.ac.nwu.translator.MemberTransactionTranslator;
import za.ac.nwu.translator.MemberTranslator;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@Component("newMemberTransaction")
public class NewTransactionServiceImpl implements NewTransactionService {
    private final MemberTransactionTranslator memberTransactionTranslator;
    private final ExchangeMediumTranslator exchangeMediumTranslator;

    @Autowired
    public NewTransactionServiceImpl(MemberTransactionTranslator memberTransactionTranslator, ExchangeMediumTranslator exchangeMediumTranslator) {
        this.memberTransactionTranslator = memberTransactionTranslator;
        this.exchangeMediumTranslator = exchangeMediumTranslator;
    }

    @Override
    public MemberTransactionDto addTransactionDto(MemberTransactionDto memberTransactionDto) {

        Exchange_Medium exchange_medium = exchangeMediumTranslator.getExchangeMediumByEmID(memberTransactionDto.getExID());

        Member_Transaction memberTransaction = memberTransactionDto.buildMemberTransaction(exchange_medium);

        Member_Transaction addedMemberTransaction = memberTransactionTranslator.addMemberTransaction(memberTransaction);

        if(null == memberTransactionDto.getTransactionDate()){
            memberTransactionDto.setTransactionDate(LocalDate.now());
            memberTransactionDto.setDescription("Example description here.");
        }
        return new MemberTransactionDto(addedMemberTransaction);
    }
}
