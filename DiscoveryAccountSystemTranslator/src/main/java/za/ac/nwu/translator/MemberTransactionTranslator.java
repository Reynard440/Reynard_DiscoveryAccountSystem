package za.ac.nwu.translator;

import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberTransactionDto;

import java.time.LocalDate;
import java.util.List;

@Component
public interface MemberTransactionTranslator {
    List<MemberTransactionDto> getMemberTransactionDtos();

    MemberTransactionDto addMemberTransaction(MemberTransactionDto memberTransactionDto);

    MemberTransactionDto getMemberTransactionID(Integer id);

    MemberTransactionDto getTransactionByIdAndDate(Integer id, LocalDate date);
}
