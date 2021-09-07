package za.ac.nwu.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.domain.persistence.Member_Transaction;

import java.time.LocalDate;

@Repository
public interface MemberTransactionRepository extends JpaRepository<Member_Transaction, Integer>{
    @Query("select m from Member_Transaction m where m.MtId = ?1")
    Member_Transaction getByMtId(Integer MtId);

    @Query("select m from Member_Transaction m where m.MtId = ?1 and m.TransactionDate = ?2")
    Member_Transaction getByMtIdAndTransactionDate(Integer MtId, LocalDate TransactionDate);

//    @Modifying
//    @Query(value = "UPDATE Member_Transaction SET MT_Total = MT_Total + :amount WHERE MemberID = :mem_id and MT_ID = :mt_id")
//    Member_Transaction increaseTotal(Integer mem_id, Integer mt_id, double amount);
//
//    @Modifying
//    @Query(value = "UPDATE Member_Transaction SET MT_Total = MT_Total - :amount WHERE MemberID = :mem_id and MT_ID = :mt_id")
//    Member_Transaction decreaseTotal(Integer mem_id, Integer mt_id, double amount);
}
