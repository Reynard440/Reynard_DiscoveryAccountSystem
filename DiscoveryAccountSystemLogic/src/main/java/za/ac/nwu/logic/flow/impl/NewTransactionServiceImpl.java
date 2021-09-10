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
//        Member_Transaction addedMemberTransaction = null;

        if(null == memberTransactionDto.getTransactionDate()){
            memberTransactionDto.setTransactionDate(LocalDate.now());
            memberTransactionDto.setDescription("Example description here.");
        }

        Exchange_Medium exchange_medium = exchangeMediumTranslator.getExchangeMediumByEmID(memberTransactionDto.getExID());

        Member_Transaction memberTransaction = memberTransactionDto.buildMemberTransaction(exchange_medium);

        Member_Transaction addedMemberTransaction = memberTransactionTranslator.addMemberTransaction(memberTransaction);

//        if (exchangeMediumTranslator.checkTypeExists(exchange_medium.getMemID().getId(), exchange_medium.getType())) {
//            //if a member with emId #1 already has an exchange medium type like MILES, code follows:
//            if (memberTransaction.getDescription().contains("Deposit") || memberTransaction.getDescription().contains("deposit")) {
//                exchangeMediumTranslator.increaseExchangeMediumTotal(memberTransaction.getEmId().getEmId(), memberTransaction.getAmount());
//            } else {
//                exchangeMediumTranslator.decreaseExchangeMediumTotal(memberTransaction.getEmId().getEmId(), memberTransaction.getAmount());
//            }
//        } else { //else the new exchange medium will be added for the specific member
//            addedMemberTransaction = memberTransactionTranslator.addMemberTransaction(memberTransaction);
//        }
//        assert addedMemberTransaction != null;
        return new MemberTransactionDto(addedMemberTransaction);
    }
}
