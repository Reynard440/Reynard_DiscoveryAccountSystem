package za.ac.nwu.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.domain.persistence.Member_Transaction;

import java.time.LocalDate;
//import za.ac.nwu.domain.persistence.Member_Transaction;

@Component
public interface MemberTransactionRepository extends JpaRepository<Member_Transaction, Integer>{
    /*@Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findUserByEmail(String email);*/

    @Query(value = "SELECT mt.MT_ID, mt.MT_TransactionDate, mt.EM_ID, mt.MemberID, mt.MT_Description, mt.MT_Amount, mt.MT_Total FROM Member_Transaction mt WHERE mt.MemberID = :id AND mt.MT_TransactionDate = :date")
    Member_Transaction getTransactionByIdAndDate(Integer id, LocalDate date);

//    @Modifying
//    @Query(value = "UPDATE Member_Transaction SET MT_Total = MT_Total + :amount WHERE MemberID = :mem_id and MT_ID = :mt_id")
//    Member_Transaction increaseTotal(Integer mem_id, Integer mt_id, double amount);
//
//    @Modifying
//    @Query(value = "UPDATE Member_Transaction SET MT_Total = MT_Total - :amount WHERE MemberID = :mem_id and MT_ID = :mt_id")
//    Member_Transaction decreaseTotal(Integer mem_id, Integer mt_id, double amount);
}
