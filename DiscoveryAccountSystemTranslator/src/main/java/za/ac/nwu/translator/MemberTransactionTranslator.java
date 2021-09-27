package za.ac.nwu.translator;

import org.springframework.stereotype.Component;
import za.ac.nwu.domain.persistence.Member_Transaction;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Component
public interface MemberTransactionTranslator {
    Member_Transaction addMemberTransaction(Member_Transaction memberTransaction) throws SQLException;

    Member_Transaction getMemberTransactionID(Integer id) throws SQLException;

    Member_Transaction getTransactionByIdAndDate(Integer id, LocalDate date) throws SQLException;
}