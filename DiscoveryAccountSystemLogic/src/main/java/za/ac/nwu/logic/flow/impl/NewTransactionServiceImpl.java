package za.ac.nwu.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.domain.persistence.Member_Transaction;
import za.ac.nwu.logic.flow.NewTransactionService;
import za.ac.nwu.translator.ExchangeMediumTranslator;
import za.ac.nwu.translator.MemberTransactionTranslator;

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

        Member_Transaction memberTransaction = memberTransactionDto.buildMemberTransaction();
        Member_Transaction addedMemberTransaction = memberTransactionTranslator.addMemberTransaction(memberTransaction);

        if (memberTransaction.getDescription().equals("Deposit") || memberTransaction.getDescription().equals("deposit")) {
            exchangeMediumTranslator.increaseExchangeMediumTotal(memberTransaction.getEmId().getEmId(), memberTransaction.getAmount());
        } else if (memberTransaction.getDescription().equals("Withdrawal") || memberTransaction.getDescription().equals("withdrawal")) {
            exchangeMediumTranslator.decreaseExchangeMediumTotal(memberTransaction.getEmId().getEmId(), memberTransaction.getAmount());
        }
        return new MemberTransactionDto(addedMemberTransaction);
    }
}
