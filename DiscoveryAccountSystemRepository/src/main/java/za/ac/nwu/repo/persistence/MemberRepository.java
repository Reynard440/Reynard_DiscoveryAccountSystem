package za.ac.nwu.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.domain.persistence.Member;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    //Optional<Member> findMemberByEmail(String Mem_Email);
    @Query("SELECT m FROM Member m WHERE m.Mem_Email = ?1")
    Optional<Member> findMemberByEmail(String email);
}
