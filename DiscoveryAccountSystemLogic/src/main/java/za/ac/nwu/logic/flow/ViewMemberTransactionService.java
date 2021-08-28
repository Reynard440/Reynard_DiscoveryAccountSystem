package za.ac.nwu.logic.flow;

import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.dto.MemberTransactionDto;

import java.util.List;

public interface ViewMemberTransactionService {
    List<MemberTransactionDto> getAllMemberTransaction();
}
