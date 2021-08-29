package za.ac.nwu.logic.flow;

import za.ac.nwu.domain.dto.MemberTransactionDto;

public interface NewTransactionService {
    MemberTransactionDto addTransactionDto(MemberTransactionDto memberTransactionDto);
}
