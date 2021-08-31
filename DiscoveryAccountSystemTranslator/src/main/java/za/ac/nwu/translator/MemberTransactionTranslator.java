package za.ac.nwu.translator;

import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberTransactionDto;

import java.util.List;

@Component
public interface MemberTransactionTranslator {
    List<MemberTransactionDto> getMemberTransactionDtos();

    MemberTransactionDto addMemberTransaction(MemberTransactionDto memberTransactionDto);
}
