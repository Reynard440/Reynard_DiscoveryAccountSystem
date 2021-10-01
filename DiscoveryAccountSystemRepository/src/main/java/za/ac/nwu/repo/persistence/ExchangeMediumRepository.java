package za.ac.nwu.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.domain.persistence.Member;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangeMediumRepository extends JpaRepository<Exchange_Medium, Integer> {
    @Query("select e from Exchange_Medium e where e.EmId = ?1")
    Exchange_Medium getByEmId(Integer EmId);

    @Query("select (count(e) > 0) from Exchange_Medium e where e.Type = ?1 and e.MemID.Id = ?2")
    boolean existsByTypeAndMemID_Id(String Type, Integer Id);

    @Query("select e from Exchange_Medium e where e.Type = ?1 and e.EmId = ?2")
    Exchange_Medium getByTypeAndEmId(String Type, Integer EmId);

    @Query("select e from Exchange_Medium e where e.MemID.Id = ?1")
    List<Exchange_Medium> getByMemID(Integer memId);

    @Query(value = "SELECT em FROM Exchange_Medium em WHERE em.Type = :type and em.EmId = :em_id")
    Exchange_Medium getExchangeMediumCurrentByTypeAndID(String type, Integer em_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Exchange_Medium SET Balance=Balance + :increase WHERE EmId = :id ")
    void increaseBalance(double increase, Integer id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Exchange_Medium SET Balance=Balance - :decrease WHERE EmId = :id ")
    void decreaseBalance(double decrease, Integer id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Exchange_Medium SET Balance=Balance * :adjust, Type=:newType, Description=:description WHERE MemID.Id=:mem and Type=:type and EmId=:id ")
    void switchExchangeMedium(String type, String newType, double adjust, Integer mem, Integer id, String description);

}
