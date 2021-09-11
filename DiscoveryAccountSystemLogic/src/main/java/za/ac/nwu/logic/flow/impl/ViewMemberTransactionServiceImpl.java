package za.ac.nwu.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import za.ac.nwu.domain.dto.ExchangeMediumDto;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.domain.persistence.Member_Transaction;
import za.ac.nwu.logic.flow.ViewMemberTransactionService;
import za.ac.nwu.translator.MemberTransactionTranslator;

import java.time.LocalDate;
import java.util.ArrayList;
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
        List<MemberTransactionDto> memberTransactionDtos = new ArrayList<>();
        for (Member_Transaction memberTransaction : memberTransactionTranslator.getMemberTransactions()) {
            memberTransactionDtos.add(new MemberTransactionDto(memberTransaction));
        }
        return memberTransactionDtos;
    }

    @Override
    public MemberTransactionDto getMemberTransactionID(Integer id) {
        Member_Transaction memberTransaction = memberTransactionTranslator.getMemberTransactionID(id);
        return null != memberTransaction ? new MemberTransactionDto(memberTransaction) : null;
    }

    @Override
    public MemberTransactionDto getTransactionByIdAndDate(Integer id, LocalDate date) {
        Member_Transaction memberTransaction = memberTransactionTranslator.getTransactionByIdAndDate(id, date);
        return null != memberTransaction ? new MemberTransactionDto(memberTransaction) : null;
    }
}
