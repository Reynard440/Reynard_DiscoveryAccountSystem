package za.ac.nwu.logic.flow;

import za.ac.nwu.domain.dto.MemberTransactionDto;

import java.time.LocalDate;
import java.util.List;

public interface ViewMemberTransactionService {
    List<MemberTransactionDto> getAllMemberTransaction();

    MemberTransactionDto getMemberTransactionID(Integer id);

    MemberTransactionDto getTransactionByIdAndDate(Integer id, LocalDate date);
}
