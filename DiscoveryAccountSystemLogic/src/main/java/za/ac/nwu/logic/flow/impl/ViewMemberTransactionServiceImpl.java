package za.ac.nwu.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.logic.flow.ViewMemberTransactionService;
import za.ac.nwu.translator.MemberTranslator;

import java.util.List;

@Transactional
@Component
public class ViewMemberTransactionServiceImpl implements ViewMemberTransactionService {

    private final MemberTranslator memberTranslator;

    @Autowired
    public ViewMemberTransactionServiceImpl(MemberTranslator memberTranslator){
        this.memberTranslator = memberTranslator;
    }

    @Override
    public List<MemberTransactionDto> getAllMemberTransaction(){
        return memberTranslator.getMemberTransactionDtos();
    }
}
