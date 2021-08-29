package za.ac.nwu.logic.flow.impl;

import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.logic.flow.NewTransactionService;
import za.ac.nwu.translator.MemberTranslator;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@Component("newMemberTransaction")
public class NewTransactionServiceImpl implements NewTransactionService {
    private final MemberTranslator memberTranslator;

    public NewTransactionServiceImpl(MemberTranslator memberTranslator){
        this.memberTranslator = memberTranslator;
    }

    @Override
    public MemberTransactionDto addTransactionDto(MemberTransactionDto memberTransactionDto) {
        if(null == memberTransactionDto.getMT_TransactionDate()){
            memberTransactionDto.setMT_TransactionDate(LocalDate.now());
        }
        return memberTranslator.addMemberTransaction(memberTransactionDto);
    }
}
