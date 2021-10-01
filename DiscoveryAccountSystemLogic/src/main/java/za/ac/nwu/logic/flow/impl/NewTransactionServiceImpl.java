package za.ac.nwu.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.domain.persistence.Member_Transaction;
import za.ac.nwu.logic.flow.NewTransactionService;
import za.ac.nwu.translator.ExchangeMediumTranslator;
import za.ac.nwu.translator.MemberTransactionTranslator;

import javax.transaction.Transactional;
import java.sql.SQLException;

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

    @Transactional
    @Override
    public MemberTransactionDto addTransactionDto(MemberTransactionDto memberTransactionDto) throws SQLException {
        if (null != memberTransactionDto.getTransactionDate() && null != memberTransactionDto.getDescription() && null != memberTransactionDto.getEmId()) {
            LOGGER.info("The input object is {}", memberTransactionDto);

            Member_Transaction memberTransaction = memberTransactionDto.buildMemberTransaction();
            Member_Transaction addedMemberTransaction = memberTransactionTranslator.addMemberTransaction(memberTransaction);

            if (memberTransaction.getDescription().contains("Withdrawal") && memberTransaction.getEmId().getBalance() > addedMemberTransaction.getAmount()) {
                exchangeMediumTranslator.decreaseExchangeMediumTotal(memberTransaction.getEmId().getEmId(), memberTransaction.getAmount());
            } else {
                exchangeMediumTranslator.increaseExchangeMediumTotal(memberTransaction.getEmId().getEmId(), memberTransaction.getAmount());
            }
            MemberTransactionDto result = new MemberTransactionDto(addedMemberTransaction);
            LOGGER.info("The return object is {}", result);
            return result;
        } else {
            throw new SQLException("Transaction is null, rolling back the transaction.");
        }
    }
}
