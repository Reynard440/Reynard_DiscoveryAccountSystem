package za.ac.nwu.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.domain.persistence.Member_Transaction;
import za.ac.nwu.logic.flow.NewTransactionService;
import za.ac.nwu.translator.ExchangeMediumTranslator;
import za.ac.nwu.translator.MemberTransactionTranslator;

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

    @Transactional(rollbackFor = { SQLException.class, RuntimeException.class })
    @Override
    public MemberTransactionDto addTransactionDto(MemberTransactionDto memberTransactionDto) throws SQLException {
        try {
            LOGGER.info("The input object is {}", memberTransactionDto);
            Member_Transaction memberTransaction = memberTransactionDto.buildMemberTransaction();
            Member_Transaction addedMemberTransaction = memberTransactionTranslator.addMemberTransaction(memberTransaction);

            if (memberTransaction.getDescription().equals("Withdrawal")) {
                exchangeMediumTranslator.decreaseExchangeMediumTotal(addedMemberTransaction.getEmId().getEmId(), memberTransaction.getAmount());
            } else if (memberTransaction.getDescription().equals("Deposit"))  {
                exchangeMediumTranslator.increaseExchangeMediumTotal(addedMemberTransaction.getEmId().getEmId(), memberTransaction.getAmount());
            } else {
                throw new SQLException("No valid transaction type was selected, rolling back the transactions.");
            }
            MemberTransactionDto result = new MemberTransactionDto(addedMemberTransaction);
            LOGGER.info("The return object is {}", result);
            return result;
        } catch (SQLException e) {
            throw new SQLException("Transaction-error occurred, rolling back the transaction.", e.getMessage());
        }
    }
}
