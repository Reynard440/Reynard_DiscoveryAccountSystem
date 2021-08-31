package za.ac.nwu.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.logic.flow.NewTransactionService;
import za.ac.nwu.translator.MemberTransactionTranslator;
import za.ac.nwu.translator.MemberTranslator;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@Component("newMemberTransaction")
public class NewTransactionServiceImpl implements NewTransactionService {
    private final MemberTransactionTranslator memberTransactionTranslator;

    @Autowired
    public NewTransactionServiceImpl(MemberTransactionTranslator memberTransactionTranslator) {
        this.memberTransactionTranslator = memberTransactionTranslator;
    }

    @Override
    public MemberTransactionDto addTransactionDto(MemberTransactionDto memberTransactionDto) {
        if(null == memberTransactionDto.getMT_TransactionDate()){
            memberTransactionDto.setMT_TransactionDate(LocalDate.now());
        }
        return memberTransactionTranslator.addMemberTransaction(memberTransactionDto);
    }
}
