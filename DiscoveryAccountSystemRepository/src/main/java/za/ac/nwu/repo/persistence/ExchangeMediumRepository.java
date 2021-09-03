package za.ac.nwu.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.domain.persistence.Exchange_Medium;

import javax.transaction.Transactional;

@Repository
public interface ExchangeMediumRepository extends JpaRepository<Exchange_Medium, Integer> {

    @Query(value = "SELECT em FROM Exchange_Medium em WHERE em.EM_Type = :type AND em.EM_ID = :id")
    Exchange_Medium getExchange_MediumByTypeAndID(String type, Integer id);

    @Query(value = "SELECT em FROM Exchange_Medium em WHERE em.EM_ID = :emid")
    Exchange_Medium getAllExchangeMediumByEmID(Integer emid);

    @Transactional
    @Query(value = "UPDATE Exchange_Medium SET EM_Balance=EM_Balance + :increase WHERE EM_ID = :id ")
    Exchange_Medium increaseBalance(double increase, Integer id);

    @Query(value = "UPDATE Exchange_Medium SET EM_Balance=EM_Balance - :decrease WHERE EM_Type = :type AND EM_ID = :id ")
    Exchange_Medium decreaseBalance(double decrease, Integer id);

    @Query(value = "SELECT COUNT(EM_ID) FROM Exchange_Medium WHERE EM_ID=:id and EM_Type=:type")
    Integer checkTypeExist(Integer id, String type);
}
