package za.ac.nwu.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.domain.persistence.Member_Transaction;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Repository
public interface MemberTransactionRepository extends JpaRepository<Member_Transaction, Integer>{
    @Query("select m from Member_Transaction m where m.MtId = ?1")
    Member_Transaction getByMtId(Integer MtId);

    @Query("select m from Member_Transaction m where m.MtId = ?1 and m.TransactionDate = ?2")
    Member_Transaction getByMtIdAndTransactionDate(Integer MtId, LocalDate TransactionDate);
}
