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
import java.time.LocalDate;

@Transactional
@Component("newMemberTransaction")
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
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("The input object is {}", memberTransactionDto);
        }
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
        MemberTransactionDto result = new MemberTransactionDto(addedMemberTransaction);
        LOGGER.info("The return object is {}", result);
        return result;
    }
}
