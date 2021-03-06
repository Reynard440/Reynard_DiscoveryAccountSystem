package za.ac.nwu.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.domain.persistence.Member_Transaction;
import za.ac.nwu.logic.flow.ViewMemberTransactionService;
import za.ac.nwu.translator.MemberTransactionTranslator;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component("viewMemberTransactionServiceFlow")
public class ViewMemberTransactionServiceImpl implements ViewMemberTransactionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewMemberTransactionServiceImpl.class);
    private final MemberTransactionTranslator memberTransactionTranslator;

    @Autowired
    public ViewMemberTransactionServiceImpl(MemberTransactionTranslator memberTransactionTranslator) {
        this.memberTransactionTranslator = memberTransactionTranslator;
    }

    @Override
    public List<MemberTransactionDto> getMemberTransactionID(Integer id) throws SQLException {
        LOGGER.info("The input for Member Transaction id is {}", id);
        List<MemberTransactionDto> memberTransactionDtos = new ArrayList<>();
        for (Member_Transaction memberTransaction : memberTransactionTranslator.getMemberTransactionID(id)) {
            memberTransactionDtos.add(new MemberTransactionDto(memberTransaction));
        }
        LOGGER.info("The return object is {} ", memberTransactionDtos);
        return memberTransactionDtos;
    }

    @Override
    public List<MemberTransactionDto> getTransactionByIdAndDate(Integer id, LocalDate date) throws SQLException {
        LOGGER.info("The input for Member Transaction id is {}", id);
        List<MemberTransactionDto> memberTransactionDtos = new ArrayList<>();
        for (Member_Transaction memberTransaction : memberTransactionTranslator.getTransactionByIdAndDate(id, date)) {
            memberTransactionDtos.add(new MemberTransactionDto(memberTransaction));
        }
        LOGGER.info("The return object is {} ", memberTransactionDtos);
        return memberTransactionDtos;
    }
}
