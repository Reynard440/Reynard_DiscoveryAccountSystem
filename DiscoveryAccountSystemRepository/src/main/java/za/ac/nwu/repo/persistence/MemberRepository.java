package za.ac.nwu.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.domain.persistence.Member;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    @Query("SELECT m FROM Member m WHERE m.Mem_Email = :email")
    Optional<Member> findMemberByEmail(String email);
//
//    //native queries are not recommended!!
//    @Query(value = "SELECT m.Mem_FirstName, m.Mem_LastName, m.Mem_Email, m.Mem_Phone_Number FROM Member m WHERE m.Mem_Email = :email", nativeQuery = true)
//    Member getMemberByEmailNativeQuery(String email);
//

    @Query(value = "SELECT m FROM Member m WHERE m.Mem_Email = :email")
    Member getMemberByEmail(String email);

    @Query(value = "SELECT m FROM Member m WHERE m.MemID = :id")
    Member getMemberById(Integer id);

    @Modifying
    @Query(value = "DELETE FROM Member m WHERE m.Mem_Phone_Number = :phone")
    void delete(String phone);

//    @Bean
//    @Query(value = "SELECT new za.ac.nwu.domain.dto.MemberDto(*) FROM Member m WHERE m.Mem_Email = :email")
//    MemberDto getMemberDtoByEmail(String email);
}
