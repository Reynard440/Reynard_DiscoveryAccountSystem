package za.ac.nwu.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.logic.flow.ViewMemberTransactionService;
import za.ac.nwu.translator.MemberTransactionTranslator;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Component
public class ViewMemberTransactionServiceImpl implements ViewMemberTransactionService {

    private final MemberTransactionTranslator memberTransactionTranslator;

    @Autowired
    public ViewMemberTransactionServiceImpl(MemberTransactionTranslator memberTransactionTranslator) {
        this.memberTransactionTranslator = memberTransactionTranslator;
    }

    @Override
    public List<MemberTransactionDto> getAllMemberTransaction(){
        return memberTransactionTranslator.getMemberTransactionDtos();
    }

    @Override
    public MemberTransactionDto getMemberTransactionID(Integer id) {
        return memberTransactionTranslator.getMemberTransactionID(id);
    }

    @Override
    public MemberTransactionDto getTransactionByIdAndDate(Integer id, LocalDate date) {
        return memberTransactionTranslator.getTransactionByIdAndDate(id, date);
    }
}
