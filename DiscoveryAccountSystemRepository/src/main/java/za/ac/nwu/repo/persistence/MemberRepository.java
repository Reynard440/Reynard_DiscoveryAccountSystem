package za.ac.nwu.repo.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.persistence.Member;

import java.util.Optional;

@Component
public interface MemberRepository extends JpaRepository<Member, Integer> {
    //Optional<Member> findMemberByEmail(String Mem_Email);

    @Query("SELECT m FROM Member m WHERE m.Mem_Email = :email")
    Optional<Member> findMemberByEmail(String email);
//
//    //native queries are not recommended!!
//    @Query(value = "SELECT m.Mem_FirstName, m.Mem_LastName, m.Mem_Email, m.Mem_Phone_Number FROM Member m WHERE m.Mem_Email = :email", nativeQuery = true)
//    Member getMemberByEmailNativeQuery(String email);
//

    @Query(value = "SELECT m.MemID, m.Mem_FirstName, m.Mem_LastName, m.Mem_Email FROM Member m WHERE m.Mem_Email = :email ")
    Member getMemberByEmail(String email);

    @Query(value = "SELECT m.MemID, m.Mem_FirstName, m.Mem_LastName, m.Mem_Email FROM Member m WHERE m.MemID = :id ")
    Member getMemberById(Integer id);

//    @Bean
//    @Query(value = "SELECT new za.ac.nwu.domain.dto.MemberDto(*) FROM Member m WHERE m.Mem_Email = :email")
//    MemberDto getMemberDtoByEmail(String email);
}
