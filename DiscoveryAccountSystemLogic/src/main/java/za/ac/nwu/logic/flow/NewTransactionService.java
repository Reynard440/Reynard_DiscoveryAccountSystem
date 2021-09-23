package za.ac.nwu.logic.flow;

import za.ac.nwu.domain.dto.MemberTransactionDto;

import java.sql.SQLException;

public interface NewTransactionService {
    MemberTransactionDto addTransactionDto(MemberTransactionDto memberTransactionDto) throws SQLException;
}
