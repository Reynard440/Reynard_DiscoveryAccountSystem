package za.ac.nwu.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.domain.persistence.Member_Transaction;
import za.ac.nwu.logic.flow.NewTransactionService;
import za.ac.nwu.translator.ExchangeMediumTranslator;
import za.ac.nwu.translator.MemberTransactionTranslator;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.time.LocalDate;

@Transactional
@Component("newMemberTransactionFlow")
public class NewTransactionServiceImpl implements NewTransactionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewTransactionServiceImpl.class);
    private final MemberTransactionTranslator memberTransactionTranslator;
    private final ExchangeMediumTranslator exchangeMediumTranslator;

    @Autowired
    public NewTransactionServiceImpl(MemberTransactionTranslator memberTransactionTranslator, ExchangeMediumTranslator exchangeMediumTranslator) {
        this.memberTransactionTranslator = memberTransactionTranslator;
        this.exchangeMediumTranslator = exchangeMediumTranslator;
    }

    @Override
    public MemberTransactionDto addTransactionDto(MemberTransactionDto memberTransactionDto) throws SQLException {
        LOGGER.info("The input object is {}", memberTransactionDto);

        Member_Transaction memberTransaction = memberTransactionDto.buildMemberTransaction();
        Member_Transaction addedMemberTransaction = memberTransactionTranslator.addMemberTransaction(memberTransaction);

        if (memberTransaction.getDescription().equals("Withdrawal") && memberTransaction.getEmId().getBalance() > 0) {
            exchangeMediumTranslator.decreaseExchangeMediumTotal(memberTransaction.getEmId().getEmId(), memberTransaction.getAmount());
        } else {
            exchangeMediumTranslator.increaseExchangeMediumTotal(memberTransaction.getEmId().getEmId(), memberTransaction.getAmount());
        }
        MemberTransactionDto result = new MemberTransactionDto(addedMemberTransaction);
        LOGGER.info("The return object is {}", result);
        return result;
    }
}
