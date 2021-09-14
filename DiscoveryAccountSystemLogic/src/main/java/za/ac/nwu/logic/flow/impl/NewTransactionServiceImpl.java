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
        if(null == memberTransactionDto.getTransactionDate()){
            memberTransactionDto.setTransactionDate(LocalDate.now());
            memberTransactionDto.setDescription("Example description here.");
        }

//        Member_Transaction memberTransaction = memberTransactionDto.buildMemberTransaction();
//        Member_Transaction addedMemberTransaction = memberTransactionTranslator.addMemberTransaction(memberTransaction);
//        //if a member with emId #1 already has an exchange medium type like MILES, code follows:
//        if (memberTransaction.getDescription().equals("Deposit") || memberTransaction.getDescription().equals("deposit")) {
//            exchangeMediumTranslator.increaseExchangeMediumTotal(memberTransaction.getEmId().getEmId(), memberTransaction.getAmount());
//        } else {
//            exchangeMediumTranslator.decreaseExchangeMediumTotal(memberTransaction.getEmId().getEmId(), memberTransaction.getAmount());
//        }
//
//        return new MemberTransactionDto(addedMemberTransaction);

        Exchange_Medium exchange_medium = exchangeMediumTranslator.getExchangeMediumByEmID(memberTransactionDto.getEmId());

        if (exchangeMediumTranslator.checkTypeExists(exchange_medium.getMemID().getId(), exchange_medium.getType())) {
            Member_Transaction memberTransaction = memberTransactionDto.buildMemberTransaction();
            Member_Transaction addedMemberTransaction = memberTransactionTranslator.addMemberTransaction(memberTransaction);
            //if a member with emId #1 already has an exchange medium type like MILES, code follows:
            if (memberTransaction.getDescription().equals("Deposit") || memberTransaction.getDescription().equals("deposit")) {
                exchangeMediumTranslator.increaseExchangeMediumTotal(memberTransaction.getEmId().getEmId(), memberTransaction.getAmount());
            } else if (memberTransaction.getDescription().equals("Withdrawal") || memberTransaction.getDescription().equals("withdrawal")) {
                exchangeMediumTranslator.decreaseExchangeMediumTotal(memberTransaction.getEmId().getEmId(), memberTransaction.getAmount());
            }
            return new MemberTransactionDto(addedMemberTransaction);
        } else { //else the new exchange medium will be added for the specific member
            Member_Transaction memberTransaction = memberTransactionDto.buildMemberTransaction(exchange_medium);
            Member_Transaction addedMemberTransaction = memberTransactionTranslator.addMemberTransaction(memberTransaction);
            addedMemberTransaction = memberTransactionTranslator.addMemberTransaction(memberTransaction);
            return new MemberTransactionDto(addedMemberTransaction);
        }
    }
}
