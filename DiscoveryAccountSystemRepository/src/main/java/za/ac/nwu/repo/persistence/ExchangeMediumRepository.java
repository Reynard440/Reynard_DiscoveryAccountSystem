package za.ac.nwu.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.nwu.domain.persistence.Exchange_Medium;

@Repository
public interface ExchangeMediumRepository extends JpaRepository<Exchange_Medium, Integer> {

}
