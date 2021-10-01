package za.ac.nwu.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.domain.persistence.Member_Transaction;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MemberTransactionRepository extends JpaRepository<Member_Transaction, Integer>{
    @Query("select m from Member_Transaction m where m.EmId.EmId = ?1")
    List<Member_Transaction> getByEmId(Integer id);

    @Query("select m from Member_Transaction m where m.EmId.EmId = ?1 and m.TransactionDate = ?2")
    List<Member_Transaction> getByMtIdAndTransactionDate(Integer id, LocalDate TransactionDate);
}
