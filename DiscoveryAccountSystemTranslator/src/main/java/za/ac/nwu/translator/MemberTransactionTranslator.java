package za.ac.nwu.translator;

import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.domain.persistence.Member_Transaction;

import java.time.LocalDate;
import java.util.List;

@Component
public interface MemberTransactionTranslator {
    List<Member_Transaction> getMemberTransactions();

    Member_Transaction addMemberTransaction(Member_Transaction memberTransaction);

    Member_Transaction getMemberTransactionID(Integer id);

    Member_Transaction getTransactionByIdAndDate(Integer id, LocalDate date);
}
