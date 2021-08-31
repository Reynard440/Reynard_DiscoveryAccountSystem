package za.ac.nwu.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.persistence.Exchange_Medium;

@Component
public interface ExchangeMediumRepository extends JpaRepository<Exchange_Medium, Integer> {

    @Query(value = "SELECT em.EM_ID, em.EM_Description, em.EM_Type, em.EM_Balance FROM Exchange_Medium em WHERE em.EM_Type = :type AND em.ExchangeID = :id")
    Exchange_Medium getExchange_MediumByTypeAndID(String type, Integer id);

    @Query(value = "SELECT em.EM_ID, em.EM_Description, em.EM_Type, em.EM_Balance FROM Exchange_Medium em WHERE em.ExchangeID = :id")
    Exchange_Medium getAllExchangeMediumByMemberID(Integer id);

    @Modifying
    @Query(value = "UPDATE Exchange_Medium SET EM_Balance=EM_Balance + :increase WHERE EM_Type = :type AND ExchangeID = :id ")
    Exchange_Medium increaseBalance(double increase, String type, Integer id);

    @Modifying
    @Query(value = "UPDATE Exchange_Medium SET EM_Balance=EM_Balance - :increase WHERE EM_Type = :type AND ExchangeID = :id ")
    Exchange_Medium decreaseBalance(double increase, String type, Integer id);
}
