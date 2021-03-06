package za.ac.nwu.logic.flow;

import za.ac.nwu.domain.dto.MemberTransactionDto;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface ViewMemberTransactionService {
    List<MemberTransactionDto> getMemberTransactionID(Integer id) throws SQLException;

    List<MemberTransactionDto> getTransactionByIdAndDate(Integer id, LocalDate date) throws SQLException;
}
