package za.ac.nwu.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.domain.persistence.Member;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    @Query("select m from Member m where m.Id = ?1")
    Member getById(Integer Id);

    @Query("select m from Member m where m.Email = ?1")
    Member getByEmail(String Email);

    @Query("delete from Member m where m.Id = ?1")
    @Modifying
    @Transactional
    void deleteById(Integer integer);
}
