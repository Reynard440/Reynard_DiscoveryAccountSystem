package za.ac.nwu.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.nwu.domain.persistence.Member_Transaction;
//import za.ac.nwu.domain.persistence.Member_Transaction;

@Repository
public interface MemberTransactionRepository extends JpaRepository<Member_Transaction, Integer>{
    /*@Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findUserByEmail(String email);*/
}
